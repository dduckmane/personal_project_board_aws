package com.project.board.domain.board.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardSaveForm {
    @NotNull
    private MultipartFile thumbNail;// 썸네일
    @Length(min = 2, max = 50)
    private String title;// 제목
    @Length( max = 50)
    @NotEmpty
    private String representativeArea;// 대표 지역
    @Length( max = 50)
    @NotEmpty
    private String detailArea;// 상세 위치
    @NotNull
    @Size(min = 1)
    private List<String> tag;// 테그는 중복으로 선택 가능
    @NotNull
    private int price;
    private String content;
    private int groupId;// 카테고리 ex) 한식, 양식, 중식, 일식
}
