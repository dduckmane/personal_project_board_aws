package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SearchByRegionAdapter implements findQueryAdapter{

    private final BoardRepository boardRepository;
    private final String regionNames="SEOUL GYEONGGI INCHEON GANG JN JS GS GN JEJU";

    @Override
    public boolean supports(Object param) {
        if(!(param instanceof String)) return false;

        String regions=regionNames;
        String region = (String) param;

        boolean contains = regions.contains(region);
        return contains;
    }

    @Override
    public Page<Board> handle(Object param, Member user, BoardSearchCondition searchCondition, Pageable pageable) {
        String region = (String) param;

        return boardRepository.searchByRegions(region,searchCondition,pageable);
    }
}
