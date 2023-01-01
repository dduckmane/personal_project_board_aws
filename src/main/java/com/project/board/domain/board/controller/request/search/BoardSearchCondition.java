package com.project.board.domain.board.controller.request.search;

import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.member.domain.searchInfo.SearchInfo.*;

@Data
@Builder
// 필터별
public class BoardSearchCondition {
    private String name;// 작성자
    private String title;// 제목
    private String all;// 작성자 + 제목
    private String price;// 가격 필터
    private String tag;// 테그 필터

    public Map<String,String> getInfo(){
        Map<String,String> info= new ConcurrentHashMap<>();

        if(name!=null) info.put(NAME, name);
        if(title!=null) info.put(TITLE, title);
        if(all!=null) info.put(ALL, all);
        if(price!=null) info.put(PRICE, price);
        if(tag!=null) info.put(TAG, tag);

        return info;
    }
}
