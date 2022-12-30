package com.project.board.domain.choiceBoard.service;

import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.repository.BoardRepository;
import com.project.board.domain.choiceBoard.domain.ChoiceBoard;
import com.project.board.domain.choiceBoard.repository.ChoiceBoardRepository;
import com.project.board.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChoiceBoardService {
    private final ChoiceBoardRepository choiceBoardRepository;
    private final BoardRepository boardRepository;

    public void choiceBoard (Member member, Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();

        ChoiceBoard choiceBoard = choiceBoardRepository
                .findByMemberAndBoard(member.getId(), boardId)
                .orElse(null);

        if (choiceBoard == null) choiceBoardRepository.save(new ChoiceBoard(member, board));
        else choiceBoardRepository.delete(choiceBoard);
    }
}
