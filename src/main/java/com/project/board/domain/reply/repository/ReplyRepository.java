package com.project.board.domain.reply.repository;

import com.project.board.domain.member.domain.Member;
import com.project.board.domain.reply.domain.Reply;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply,Long>,ReplyRepositoryCustom{
    @EntityGraph(attributePaths = {"member"})
    List<Reply> findByMember(Member member);
}
