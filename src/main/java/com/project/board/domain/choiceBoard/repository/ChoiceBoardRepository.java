package com.project.board.domain.choiceBoard.repository;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.choiceBoard.domain.ChoiceBoard;
import com.project.board.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChoiceBoardRepository extends JpaRepository<ChoiceBoard, Long> {
    Optional <ChoiceBoard> findByMember(Member member);

    // member 별 board 를 받아 찜 board 를 찾는다.
    @Query("select cb from ChoiceBoard cb where cb.member.id = :memberId and cb.board.id =:boardId")
    Optional <ChoiceBoard> findByMemberAndBoard (@Param("memberId") Long memberId, @Param("boardId") Long boardId);

    //member 가 찜한 전체를 search
    @Query ("select cb.board from ChoiceBoard cb where cb.member.id = :memberId")
    List <Board> searchAll(@Param("memberId") Long memberId);

    List <ChoiceBoard> findByBoard(Board board);

    List <ChoiceBoard> findChoiceBoardListByMember(Member member);
}
