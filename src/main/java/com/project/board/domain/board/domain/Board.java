package com.project.board.domain.board.domain;

import com.project.board.domain.base.BaseEntity;
import com.project.board.domain.member.domain.Member;
import com.project.board.domain.reply.domain.Reply;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    private String title;
    private String content;
    private int groupId;
    private Long viewCnt=0L;// 조회수
    @OneToMany(mappedBy = "board"
            ,orphanRemoval = true
            ,cascade = CascadeType.ALL)
    private List<Reply>replies=new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    //썸네일 이미지
    @Embedded
    private UploadFile thumbNail;
    @Embedded
    private Address address;
    private int price;

    private String tagSum;// tag 는 합쳐서 store!

    public void update(
            String title
            , String content
            , UploadFile thumbNail
            , Address address
            , int price
            , String renewTag
    ) {
        this.title=title;
        this.content=content;
        this.address=address;
        this.price=price;
        this.tagSum=renewTag;

        if(thumbNail!=null) this.thumbNail=thumbNail;
    }

    //합쳐져 있던 tag 들을 list 로 return
    public List<String> getTag(){
        if(this.tagSum.length()>0){
            return Arrays.asList(this.tagSum.split(","));
        }
        return new ArrayList<>();
    }
    public static Board write(
            Member member
            ,int groupId
            ,String title
            ,String content
            ,UploadFile thumbNail
            ,Address address
            , int price
            , String tag
    ){
        Board board = new Board();

        board.title=title;
        board.member=member;
        board.groupId=groupId;
        board.content=content;
        board.thumbNail=thumbNail;
        board.address=address;
        board.price=price;
        board.tagSum=tag;

        return board;
    }

    public String substringTitle() {
        // 만약에 글제목이 12글자 이상이라면
        // 12글자만 보여주고 나머지는 ...처리
        String title = this.getTitle();
        if (title.length() > 12) {
            String subStr = title.substring(0, 12);
            return subStr+"..";
        } else {
            return title;
        }
    }
    // 권한처리
    public boolean checkMySelf(String username){
        return member.getUsername().equals(username);
    }
    // 새로운 개시글인 지 확인
    public Boolean checkNewArticle() {
        LocalDateTime newArticleDate = this.getCreatedDate().plusMinutes(5);
        return LocalDateTime.now().isBefore(newArticleDate);

    }
    // 조회수 증가
    public void plusViewCnt(){
         this.viewCnt += 1;
    }
}