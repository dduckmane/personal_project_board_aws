package com.project.board.domain.member.domain.searchInfo.searchCnt;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.MapKeyColumn;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.board.domain.boardenum.Regions.*;
import static com.project.board.domain.member.domain.searchInfo.SearchInfo.REGION;
import static com.project.board.global.util.OrderUtils.order;

@NoArgsConstructor
@Embeddable
@Data
public class RegionsCnt implements AddCnt {
    private int regionsOption1;
    private int regionsOption2;
    private int regionsOption3;
    private int regionsOption4;
    private int regionsOption5;
    private int regionsOption6;
    private int regionsOption7;
    private int regionsOption8;
    private int regionsOption9;

    @Override
    public Boolean support(String name) {
        return name.equals(REGION);
    }

    public void addCnt(String region) {
        if(region.equals(SEOUL.toString())) regionsOption1++;
        if(region.equals(GYEONGGI.toString())) regionsOption2++;
        if(region.equals(INCHEON.toString())) regionsOption3++;
        if(region.equals(GANG.toString())) regionsOption4++;
        if(region.equals(JN.toString())) regionsOption5++;
        if(region.equals(JS.toString())) regionsOption6++;
        if(region.equals(GS.toString())) regionsOption7++;
        if(region.equals(GN.toString())) regionsOption8++;
        if(region.equals(JEJU.toString())) regionsOption9++;
    }

    public int getScore(String region){

        if(region.equals(SEOUL.toString())) return regionsOption1;
        if(region.equals(GYEONGGI.toString())) return regionsOption2;
        if(region.equals(INCHEON.toString())) return regionsOption3;
        if(region.equals(GANG.toString())) return regionsOption4;
        if(region.equals(JN.toString())) return regionsOption5;
        if(region.equals(JS.toString())) return regionsOption6;
        if(region.equals(GS.toString())) return regionsOption7;
        if(region.equals(GN.toString())) return regionsOption8;
        if(region.equals(JEJU.toString())) return regionsOption9;

        throw new IllegalArgumentException("잘못된 name");
    }

}
