package com.project.board.domain.member.service;

import com.project.board.domain.board.controller.request.search.ListParam;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.board.service.BoardService;
import com.project.board.domain.choiceBoard.repository.ChoiceBoardRepository;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final SearchInfoService searchInfoService;
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final ReplyRepository replyRepository;
    private final ChoiceBoardRepository choiceBoardRepository;
    //정보를 수집
    public void collectInfo(
            Member member
            , ListParam listParam
            , BoardSearchCondition searchCondition
    ) {
        Map<String, String> info = listParam.getInfo();
        Map<String, String> info1 = searchCondition.getInfo();

        info.putAll(info1);

        info.entrySet().stream()
                .filter(entry -> !entry.getValue().equals("sort"))
                .forEach(entry ->{
                    searchInfoService.addCnt(member,entry.getKey(), entry.getValue());
                 });

    }
    // 회원 탈퇴
    public void withdrawal(Member member){ // 자식 테이블을 모두 제횡
        replyRepository
                .findByMember(member)
                .stream()
                .forEach(reply -> { replyRepository.delete(reply);});
        choiceBoardRepository
                .findChoiceBoardListByMember(member)
                .stream()
                .forEach(choiceBoard -> {choiceBoardRepository.delete(choiceBoard);});
        boardRepository
                .findBoardByMember(member)
                .stream()
                .forEach(board -> { boardService.delete(board.getId());});

        memberRepository.delete(member);
    }
}
