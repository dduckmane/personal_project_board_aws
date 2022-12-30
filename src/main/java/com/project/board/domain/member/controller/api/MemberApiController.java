package com.project.board.domain.member.controller.api;

import com.project.board.domain.member.controller.request.ChoiceBoard;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.member.service.MemberService;
import com.project.board.global.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    @PostMapping("/choice")
    public void selectedBoard(
            @AuthenticationPrincipal PrincipalDetails principalDetails
            , @RequestBody ChoiceBoard choiceBoard
            ){

        memberService.choiceBoard(
                choiceBoard.getBoardId()
                , principalDetails.getMember());
    }
    @GetMapping("/choice")
    public List<Long> findChoiceBoardId(
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        return memberRepository
                .findByUsername(principalDetails.getUsername())
                .orElseThrow()
                .getChoiceBoard();
    }

}
