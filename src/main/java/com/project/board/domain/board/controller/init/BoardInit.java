package com.project.board.domain.board.controller.init;

import com.project.board.domain.board.domain.boardenum.Category;
import com.project.board.domain.board.domain.boardenum.Regions;
import com.project.board.domain.board.domain.boardenum.Tag;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.project.board.domain.board.domain.boardenum.Category.*;
import static com.project.board.domain.board.domain.boardenum.Regions.*;
import static com.project.board.domain.board.domain.boardenum.Tag.*;

@Component
@Getter
//ModelAttribute 에 담을 값들을 init
public class BoardInit {
    private List<Tag> tags=new ArrayList<>();
    private List<Category> categories=new ArrayList<>();
    private List<Regions> regions =new ArrayList<>();


    @PostConstruct
    public void init(){
        initCategory();
        initTags();
        initRegions();
    }
    // 한식, 양식, 중식, 일식 init
    public void initCategory(){
        categories.add(KOREAN);
        categories.add(AMERICA);
        categories.add(CHINA);
        categories.add(JAPAN);

    }
    // 태그 init ex) 가성비, 예약 가능, 분위기, 놀기 좋은
    public void initTags(){
        tags.add(PRICE);
        tags.add(RESERVATION);
        tags.add(MOOD);
        tags.add(PLAY);

    }
    // 지역별을 init
    public void initRegions(){
        regions.add(SEOUL);
        regions.add(GYEONGGI);
        regions.add(INCHEON);
        regions.add(GANG);
        regions.add(JS);
        regions.add(JN);
        regions.add(GS);
        regions.add(GN);
        regions.add(JEJU);
    }



}
