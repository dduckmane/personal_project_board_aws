package com.project.board.domain.board.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Data
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {
    private String representativeArea;// 대표 지역
    private String detailArea;// 상세 지역
}
