package com.project.board.domain.member.repository;


import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SearchInfoRepository extends JpaRepository<SearchInfo,Long> {

    @EntityGraph(attributePaths = {"member"})
    Optional<SearchInfo> findByMember(Member member);

    @Query("select si from SearchInfo si where si.member.id = :memberId")
    Optional <SearchInfo> findSearchInfoByMember(@Param("memberId") Long memberId);


}
