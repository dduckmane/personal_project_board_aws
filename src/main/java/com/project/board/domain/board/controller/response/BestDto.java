package com.project.board.domain.board.controller.response;

import com.project.board.domain.board.domain.Board;
import lombok.Data;

@Data
public class BestDto {
    private String name;
    private Long viewCnt;
    private Long itemId;

    public BestDto(Board board)  {
        this.name = board.getTitle();
        this.viewCnt = board.getViewCnt();
        this.itemId= board.getId();

    }
}
