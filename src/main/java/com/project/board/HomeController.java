package com.project.board;

import com.project.board.domain.board.controller.init.BoardInit;
import com.project.board.domain.board.controller.response.BestDto;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final BoardRepository boardRepository;
    private final BoardInit boardInit;


    @ModelAttribute("regions")
    public List<Regions> regions(){
        return boardInit.getRegions();
    }

    @GetMapping({ "", "/" })
    public String index(
            @PageableDefault(page = 0) Pageable pageable
            , Model model
    ) {
        model.addAttribute("items"
                , boardRepository
                        .searchBestInfo(pageable)
                        .map(BestDto::new).
                        getContent()
        );

        return "index";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }
}
