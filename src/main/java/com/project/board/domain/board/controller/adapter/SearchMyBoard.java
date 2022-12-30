package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.project.board.domain.board.boardConst.BoardConst.CHOICE;
import static com.project.board.domain.board.boardConst.BoardConst.MYBOARD;

@Component
@RequiredArgsConstructor
//찜 목록 관련 adapter
public class SearchMyBoard implements findQueryAdapter{

    private final BoardRepository boardRepository;

    @Override
    public boolean supports(Object param) {
        if(!(param instanceof String)) return false;

        String changeParam = (String) param;
        return changeParam.equals(MYBOARD);
    }

    @Override
    public Page<Board> handle(Object param, Member user, BoardSearchCondition searchCondition, Pageable pageable) {
        return boardRepository.searchMyBoard(user, searchCondition ,pageable);
    }
}
