package com.project.board.domain.board.repository;

import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.choiceBoard.domain.QChoiceBoard;
import com.project.board.domain.member.domain.Member;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.project.board.domain.board.domain.QBoard.board;
import static com.project.board.domain.choiceBoard.domain.QChoiceBoard.*;
import static com.project.board.domain.member.domain.QMember.member;
import static org.apache.logging.log4j.util.Strings.isEmpty;


public class BoardRepositoryImpl implements BoardRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    public BoardRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    //카테고리별 조회
    @Override
    public Page<Board> searchAllCondition(
            int groupId
            , BoardSearchCondition searchCondition
            , Pageable pageable
    ) {

        List<Board> result = queryFactory
                .select(board).distinct()
                .from(board)
                .join(board.member,member).fetchJoin()
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , board.groupId.eq(groupId)
                )
                .orderBy(boardSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count()).distinct()
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , board.groupId.eq(groupId)
                )
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }

    @Override
    // 지역별 조회
    public Page<Board> searchByRegions(
            String regions
            , BoardSearchCondition searchCondition
            , Pageable pageable
    ) {

        List<Board> result = queryFactory
                .select(board)
                .from(board)
                .join(board.member,member).fetchJoin()
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , board.address.representativeArea.like(regions)
                )
                .orderBy(boardSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count())
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , board.address.representativeArea.like(regions)
                )
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }
    @Override
    // 전체 조회
    public Page<Board> searchAll(BoardSearchCondition searchCondition, Pageable pageable) {
        List<Board> result = queryFactory
                .select(board)
                .from(board)
                .join(board.member,member).fetchJoin()
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                )
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count())
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                )
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }
    @Override
    //찜 목록 조회
    public Page<Board> searchByChoice(
            Member user
            , BoardSearchCondition searchCondition
            , Pageable pageable
    ) {
        List<Board> result = queryFactory
                .select(board)
                .from(board)
                .join(choiceBoard).on(board.id.eq(choiceBoard.board.id))
                .join(member).on(member.id.eq(choiceBoard.member.id))
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , choiceBoard.member.eq(user)
                )
                .orderBy(boardSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count())
                .from(board)
                .join(choiceBoard).on(board.id.eq(choiceBoard.board.id))
                .join(member).on(member.id.eq(choiceBoard.member.id))
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , choiceBoard.member.eq(user)
                )
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }

    @Override
    // 내가 쓴 게시글
    public Page<Board> searchMyBoard(
            Member user
            , BoardSearchCondition searchCondition
            , Pageable pageable
    ) {

        List<Board> result = queryFactory
                .select(board)
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , member.id.eq(user.getId())
                )
                .orderBy(boardSort(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                ;
        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count())
                .from(board)
                .join(board.member,member)
                .where(
                        usernameOrTitleEq(searchCondition.getAll())
                        , usernameEq(searchCondition.getName())
                        , titleEq(searchCondition.getTitle())
                        , filteringPrice(searchCondition.getPrice())
                        , filteringTag(searchCondition.getTag())
                        , member.id.eq(user.getId())
                )
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }

    //조회수 별 맛집 BEST 조회
    public Page<Board> searchBestInfo(Pageable pageable) {
        List<Board> result = queryFactory
                .selectFrom(board)
                .orderBy(board.viewCnt.desc())
                .offset(pageable.getOffset())
                .limit(6l)
                .fetch();

        JPAQuery<Long> CountQuery = queryFactory
                .select(board.count())
                .from(board)
                ;

        return PageableExecutionUtils.getPage(result,pageable,CountQuery::fetchOne);
    }


    // 정렬 조건
    private OrderSpecifier<?> boardSort(Pageable page) {
        if (!page.getSort().isEmpty()) {
            for (Sort.Order order : page.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "id":
                        return new OrderSpecifier(direction, board.id);
                    case "createdDateASC":
                        return new OrderSpecifier(Order.ASC, board.createdDate);
                    case "createdDateDESC":
                        return new OrderSpecifier(Order.DESC, board.createdDate);
                    case "viewCnt":
                        return new OrderSpecifier(Order.DESC, board.viewCnt);
                }
            }
        }
        return null;
    }
    private BooleanExpression usernameEq(String username){
        return !isEmpty(username) ? member.name.contains(username) : null;
    }
    private BooleanExpression titleEq(String title){
        return !isEmpty(title) ? board.title.contains(title) : null;
    }
    private BooleanExpression usernameOrTitleEq(String all){
        return !isEmpty(all) ? board.title.contains(all).or(member.name.contains(all)) : null;
    }
    private BooleanExpression filteringTag(String tag){
        return !isEmpty(tag) ? board.tagSum.contains(tag) : null;
    }
    private BooleanExpression filteringPrice(String price){
        if(isEmpty(price)) return null;
        if(price.equals("10000")) return board.price.loe(10000);
        else if(price.equals("20000")) return board.price.gt(10000).and(board.price.loe(20000));
        else if(price.equals("30000")) return board.price.gt(20000).and(board.price.loe(30000));
        else return board.price.gt(30000);
    }
}
