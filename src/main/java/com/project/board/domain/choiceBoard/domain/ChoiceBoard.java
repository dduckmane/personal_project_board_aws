package com.project.board.domain.choiceBoard.domain;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 찜 목록 중간 테이블, 단방향 설계
public class ChoiceBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "choice_board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public ChoiceBoard(Member member, Board board) {
        this.member = member;
        this.board = board;
    }
}
