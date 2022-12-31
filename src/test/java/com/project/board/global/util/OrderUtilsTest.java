package com.project.board.global.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.project.board.global.util.OrderUtils.order;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class OrderUtilsTest {

    @Test
    void orderTest() {
        //given
        String[] orders = {
                "5option1"
                , "2option2"
                , "4option3"
                , "4option4"
                , "0"
        };
        Arrays.sort(orders);

        Map<String,Integer> orderMap=new ConcurrentHashMap<>();
        //when
        order(0,orders,orderMap,orders.length-1,0);
        //then
        orderMap.entrySet().stream().forEach(entry -> {
            System.out.println("entry.getKey() = " + entry.getKey());
            System.out.println("entry.getValue() = " + entry.getValue());
        });
        assertAll(
            () -> assertThat(orderMap.get("option1")).isEqualTo(2)
            , () -> assertThat(orderMap.get("option2")).isEqualTo(2)
            , () -> assertThat(orderMap.get("option3")).isEqualTo(3)
            , () -> assertThat(orderMap.get("option4")).isEqualTo(1)
        );

        
    }
}