package com.project.board.domain.member.controller.api;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.choiceBoard.repository.ChoiceBoardRepository;
import com.project.board.domain.choiceBoard.service.ChoiceBoardService;
import com.project.board.domain.member.controller.request.ChoiceBoard;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.member.service.MemberService;
import com.project.board.global.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberApiController {
    private final ChoiceBoardService choiceBoardService;
    private final ChoiceBoardRepository choiceBoardRepository;
    //찜을 한다.
    @PostMapping("/choice")
    public void selectedBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @RequestBody ChoiceBoard choiceBoard
            ){
        choiceBoardService.choiceBoard(
                principalDetails.getMember()
                , choiceBoard.getBoardId());
    }
    //찜한 목록을 보여준다.
    @GetMapping("/choice")
    public List<Long> findChoiceBoardId(
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        return choiceBoardRepository
                .searchAll(principalDetails.getMember().getId())
                .stream()
                .map(Board::getId)
                .collect(Collectors.toList());
    }

}
