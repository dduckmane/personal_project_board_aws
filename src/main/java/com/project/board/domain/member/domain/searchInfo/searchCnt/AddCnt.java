package com.project.board.domain.member.domain.searchInfo.searchCnt;

/**
 * 사용자 검색 정보의 횟 수를 증가시키는 interface
 **/
public interface AddCnt {
    Boolean support(String name);
    void addCnt(String name);
}
