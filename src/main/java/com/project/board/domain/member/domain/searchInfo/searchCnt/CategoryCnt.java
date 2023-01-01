package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.MapKeyColumn;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.member.domain.searchInfo.SearchInfo.CATEGORY;
import static com.project.board.global.util.OrderUtils.order;

@Data
@Embeddable
@NoArgsConstructor
// 카테고리별 횟 수를 증가시킨다.
public class CategoryCnt implements AddCnt {
    private int categoryOption1;
    private int categoryOption2;
    private int categoryOption3;
    private int categoryOption4;


    @Override
    public Boolean support(String name) {
        return name.equals(CATEGORY);
    }
    @Override
    public void addCnt(String region){
        if(region.equals("1")) categoryOption1++;
        if(region.equals("2")) categoryOption2++;
        if(region.equals("3")) categoryOption3++;
        if(region.equals("4")) categoryOption4++;
    }

    public int getScore(int groupId){
        if(groupId ==1) return categoryOption1;
        if(groupId ==2) return categoryOption2;
        if(groupId ==3) return categoryOption3;
        if(groupId ==4) return categoryOption4;

        throw new IllegalArgumentException("wrong option");

    }
}
