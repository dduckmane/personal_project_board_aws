package com.project.board.domain.board.controller.adapter;

import com.project.board.domain.board.boardConst.BoardConst;
import com.project.board.domain.board.controller.request.search.ListParam;
import com.project.board.domain.board.controller.request.search.BoardSearchCondition;
import com.project.board.domain.board.domain.Board;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.member.domain.searchInfo.SearchInfo;
import com.project.board.domain.member.repository.MemberRepository;
import com.project.board.domain.member.repository.SearchInfoRepository;
import com.project.board.domain.member.service.MemberService;
import com.project.board.global.domainConst.MemberConst;
import com.project.board.global.testInit.BoardTestInit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SearchByRecommendAdapterTest extends BoardTestInit {

    @Autowired
    SearchByRecommendAdapter searchByRecommendAdapter;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    SearchInfoRepository searchInfoRepository;

    @Test
    void supports() {
        //given
        String param = BoardConst.RECOMMEND;
        //when
        boolean supports = searchByRecommendAdapter.supports(param);
        //then
        assertThat(supports).isEqualTo(true);
    }

    @Test
    void handle() {
        //given
        boardRepoHelper.boardListInit();
        Member member = memberRepository.findByUsername(MemberConst.USERNAME).orElseThrow();

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("id"));
        // ?????? ??????
        SearchInfo searchInfo1 = new SearchInfo(member);
        searchInfoRepository.save(searchInfo1);
        
        SearchInfo searchInfo = searchInfoRepository.findSearchInfoByMember(member.getId()).orElseThrow();
        
        ListParam listParam = new ListParam(null, null);
        //category ??? ??????
        ListParam listParam1 = new ListParam(1, null);
        //?????? ??? ??????
        ListParam listParam2 = new ListParam(null, Regions.SEOUL.toString());
        //?????? ????????? ??????
        ListParam listParam3 = new ListParam(null, BoardConst.RECOMMEND);
        //??? ????????? ??????
        ListParam listParam4 = new ListParam(null, BoardConst.CHOICE);
        //????????? ?????? tag ??? ??????
        BoardSearchCondition boardSearchCondition1 = BoardSearchCondition.builder()
                .tag(Tag.MOOD.toString())
                .build();
        // ????????? ?????? ???????????? ??????
        BoardSearchCondition boardSearchCondition2 = BoardSearchCondition.builder()
                .price("10000")
                .build();
        BoardSearchCondition boardSearchCondition3 = BoardSearchCondition.builder()
                .name("name")
                .build();
        BoardSearchCondition boardSearchCondition4 = BoardSearchCondition.builder()
                .title("?????????")
                .build();
        BoardSearchCondition boardSearchCondition5 = BoardSearchCondition.builder()
                .all("?????????")
                .build();
        memberService.collectInfo(member,listParam1,boardSearchCondition1);
        memberService.collectInfo(member,listParam2,boardSearchCondition1);
        memberService.collectInfo(member,listParam3,boardSearchCondition1);
        memberService.collectInfo(member,listParam4,boardSearchCondition1);
        memberService.collectInfo(member,listParam,boardSearchCondition2);
        memberService.collectInfo(member,listParam,boardSearchCondition3);
        memberService.collectInfo(member,listParam,boardSearchCondition4);
        memberService.collectInfo(member,listParam,boardSearchCondition5);
        //?????? ??????
        // ?????? - 1???, ?????? - 1???, ????????? ?????? - 4???, ?????? 10000?????? 1???, ????????? 2???
        
        //when
        Page<Board> boards = searchByRecommendAdapter.handle(listParam, member, BoardSearchCondition.builder().build(), pageRequest);
        
        boards.getContent().stream().forEach(board -> {
            System.out.println("board.getId() = " + board.getId());
            System.out.println("board.getId() = " + board.getPrice());
            System.out.println("board.getId() = " + board.getTagSum());
            System.out.println("board.getId() = " + board.getAddress().getRepresentativeArea());
            System.out.println("board.getId() = " + board.getTitle());
            System.out.println("searchInfo.getTotalScore(board) = " + searchInfo.getTotalScore(board));
        });
    }
}