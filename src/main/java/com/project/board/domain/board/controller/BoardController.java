package com.project.board.domain.board.controller;

import com.project.board.domain.board.controller.init.BoardInit;
import com.project.board.domain.board.controller.request.BoardSaveForm;
import com.project.board.domain.board.controller.request.BoardUpdateForm;
import com.project.board.domain.board.controller.request.search.ListParam;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.controller.response.BoardDetailsDto;
import com.project.board.domain.board.controller.response.BoardListDto;
import com.project.board.domain.board.domain.Address;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.UploadFile;
import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.board.service.BoardService;
import com.project.board.domain.member.service.MemberService;
import com.project.board.global.config.auth.PrincipalDetails;
import com.project.board.global.page.PageMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final QueryAdapterHandler adapterHandler;
    private final MemberService memberService;
    private final BoardInit boardInit;
    @Value("${custom.upload.path}")
    private String UPLOAD_PATH;

    @ModelAttribute("tags")// 태그 별
    public List<Tag> tag() {
        return boardInit.getTags();
    }

    @ModelAttribute("categories") // 카테고리 별 ex) 한식, 양식, 중식, 일식
    public List<Category> categories() {
        return boardInit.getCategories();
    }

    @ModelAttribute("regions") // 지역 별 ex) 서울, 경기, 인천...
    public List<Regions> regions() {
        return boardInit.getRegions();
    }

    @GetMapping("/list")
    public String main(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @ModelAttribute("listParam") ListParam listParam
            , BoardSearchCondition searchCondition
            , @PageableDefault(page = 0, size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , Model model
    ) {
        // listParam(리스트에 들어오는 requestParam) 에 따른 클래스 제공
        // Adapter Pattern
        Page<BoardListDto> result = adapterHandler
                .service(
                        listParam.getParam()
                        , principalDetails.getMember()
                        , searchCondition
                        , pageable
                )
                .map(BoardListDto::new);

        // 추천 페이지를 위한 사용자 검색 정보를 수집
        memberService.collectInfo(principalDetails.getMember(), listParam, searchCondition);

        int nowPage = result.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, result.getTotalPages());

        model.addAttribute("BoardDtoList", result.getContent());
        model.addAttribute("Param", listParam.getParam());
        model.addAttribute("requestParam", listParam.getRequest());
        model.addAttribute("title", listParam.getTitle(principalDetails.getMember()));


        PageMaker pageMaker = new PageMaker(
                nowPage
                , endPage
                , startPage
                , result.isFirst()
                , result.isLast()
                , result.getTotalPages());

        model.addAttribute("pageMaker", pageMaker);
        return "board/board-list";
    }

    @GetMapping("/{boardId}")
    public String board(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @PathVariable Long boardId
            , HttpServletResponse response
            , HttpServletRequest request
            , Model model
    ) {
        Optional<Board> findBoard = boardService.findOne(boardId, response, request);

        BoardDetailsDto boardDetailsDto = findBoard.map(BoardDetailsDto::new).orElseThrow();

        // 권한처리
        boolean checkMyself = boardService.checkMyself(principalDetails.getMember(), findBoard.orElseThrow());

        model.addAttribute("checkMySelf", checkMyself);
        model.addAttribute("boardDetailsDto", boardDetailsDto);

        return "board/board-detail";
    }

    @GetMapping("/save/{groupId}")
    public String saveForm(
            @PathVariable int groupId
            , @ModelAttribute BoardSaveForm boardSaveForm
    ) {
        boardSaveForm.setGroupId(groupId);

        return "board/board-write";
    }

    @PostMapping("/save/{groupId}")
    public String save(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @Validated @ModelAttribute BoardSaveForm boardSaveForm
            , BindingResult bindingResult
            , @PathVariable int groupId
    ) {
        if (bindingResult.hasErrors()) return "board/board-write";

        boardService.save(
                principalDetails.getMember()
                , groupId
                , boardSaveForm.getTitle()
                , boardSaveForm.getContent()
                , UploadFile.createUploadFile(boardSaveForm.getThumbNail(), UPLOAD_PATH)
                , new Address(
                        boardSaveForm.getRepresentativeArea()
                        , boardSaveForm.getDetailArea())
                , boardSaveForm.getPrice()
                , boardSaveForm.getTag()
        );
        return "redirect:/user/board/list?groupId={groupId}";
    }

    @GetMapping("/edit/{boardId}")
    public String editForm(
            @PathVariable Long boardId
            , Model model) {

        BoardUpdateForm boardUpdateForm = boardRepository
                .findById(boardId)
                .map(BoardUpdateForm::new)
                .orElseThrow();

        model.addAttribute("boardUpdateForm", boardUpdateForm);
        return "board/board-modify";
    }

    @PostMapping("/edit")
    public String edit(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @Validated @ModelAttribute BoardUpdateForm boardUpdateForm
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
    ) {
        boardRepository
                .findMemberById(boardUpdateForm.getId())
                .orElseThrow().
                checkMySelf(principalDetails.getUsername());

        UploadFile uploadFile = null;// 수정하지 않는다면 기존 썸네일을 유지

        if(boardUpdateForm.getThumbNail().getSize()!=0)
            uploadFile = UploadFile
                    .createUploadFile(boardUpdateForm.getThumbNail(), UPLOAD_PATH);


        if (bindingResult.hasErrors()) return "board/board-modify";

        boardService.update(
                boardUpdateForm.getId()
                , boardUpdateForm.getTitle()
                , boardUpdateForm.getContent()
                , uploadFile
                , new Address(
                        boardUpdateForm.getRepresentativeArea()
                        , boardUpdateForm.getDetailArea())
                , boardUpdateForm.getPrice()
                , boardUpdateForm.getTag()
        );

        redirectAttributes.addAttribute("boardId",boardUpdateForm.getId());
        return "redirect:/user/board/{boardId}";
    }

    @GetMapping("/delete/{boardId}")
    public String delete(@PathVariable Long boardId) {
        boardService.delete(boardId);
        return "redirect:/";
    }

}


















