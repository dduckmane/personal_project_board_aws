package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static com.project.board.domain.board.boardConst.BoardConst.CHOICE;

@Component
@RequiredArgsConstructor
//찜 목록 관련 adapter
public class SearchByChoiceAdapter implements findQueryAdapter{

    private final BoardRepository boardRepository;

    @Override
    public boolean supports(Object param) {
        if(!(param instanceof String)) return false;

        String changeParam = (String) param;
        return changeParam.equals(CHOICE);
    }

    @Override
    public Page<Board> handle(Object param, Member user, BoardSearchCondition searchCondition, Pageable pageable) {

        return boardRepository.searchByChoice(user, searchCondition ,pageable);
    }
}
