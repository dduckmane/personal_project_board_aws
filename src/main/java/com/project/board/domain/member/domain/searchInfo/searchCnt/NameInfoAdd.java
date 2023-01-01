package com.project.board.domain.member.domain.searchInfo.searchCnt;

import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.MapKeyColumn;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.domain.member.domain.searchInfo.SearchInfo.*;

@NoArgsConstructor
@Embeddable
@Data
// 사용자 검색 기록을 바탕으로 횟 수와 함께 저장한다.
public class NameInfoAdd implements AddCnt {
    @ElementCollection // 값타입 취급
    @MapKeyColumn
    // 사용자가 검색한 내용을 횟 수와 함께 map 에 저장
    private Map<String,Integer> orderMap=new ConcurrentHashMap<>();

    @Override
    public Boolean support(String name) {
        // 제목이랑 제목 + 작성자 로 오는 경우만 허용
        return name.equals(TITLE) || name.equals(ALL);
    }

    @Override
    public void addCnt(String title) {

        for (String name : getNameByBlank(title)) {
            String duplication=null; // 중복을 확인

            duplication = orderMap.keySet().stream()
                    .filter(key -> key.equals(name))
                    .findFirst()
                    .orElse(null);
            //중복이 있다면 값을 증가 없다면 새로 추가
            orderMap.put(name,duplication==null? 1: orderMap.get(name)+1);
        }
    }

    public int getScore(String title){ //board 의 제목을 받음
        int sum=0;
        List<String> nameByBlank = getNameByBlank(title); // 공백을 제거하고 리스트 return
        Set<Map.Entry<String, Integer>> entries = orderMap.entrySet();

        for (String search : nameByBlank) {
            for (Map.Entry<String, Integer> entry : entries) {
                if(search.contains(entry.getKey())) sum+= entry.getValue(); // 제목에 검색 정보에 포함이 된다면 점수를 증가
            }
        }
        return sum;
    }

    public List<String> getNameByBlank(String name){
        if(name.length()>0){
            return Arrays.asList(name.split("\\s+"));
        }
        return new ArrayList<>();
    }
}
