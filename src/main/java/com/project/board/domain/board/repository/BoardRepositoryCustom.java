package com.project.board.domain.board.repository;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

    Page<Board> searchAllCondition(int groupId, BoardSearchCondition searchCondition, Pageable pageable);

    Page<Board> searchByRegions(String regions, BoardSearchCondition searchCondition, Pageable pageable);

    Page<Board> searchAll(BoardSearchCondition searchCondition, Pageable pageable);

    Page<Board> searchByChoice(Member user, BoardSearchCondition searchCondition, Pageable pageable);

    Page<Board> searchMyBoard(Member user, BoardSearchCondition searchCondition, Pageable pageable);

    Page<Board> searchBestInfo(Pageable pageable);

}
