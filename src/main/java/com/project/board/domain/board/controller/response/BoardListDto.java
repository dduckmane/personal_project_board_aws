package com.project.board.domain.board.controller.response;

import com.project.board.domain.board.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//리스트 페이지
public class BoardListDto {
    private Long id;
    private String subTitle;
    private Long viewCnt;
    private String name;
    private Boolean newArticle;

    public BoardListDto(Board board) {
        this.id=board.getId();
        this.subTitle = board.substringTitle();
        this.viewCnt = board.getViewCnt();
        this.name = board.getMember().getName();
        this.newArticle=board.checkNewArticle();
    }

}
