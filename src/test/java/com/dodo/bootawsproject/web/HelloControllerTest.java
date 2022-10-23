package com.dodo.bootawsproject.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // test 진행 시, junit 내장 실행자 외의 실행자도 실행시키도록 함. 스프링부트 테스트와 junit의 연결자
@WebMvcTest(controllers = HelloController.class) // springMvc(web)에 집중하는 스프링 테스트 어노테이션. @Component와 같은 annotation 사용 불가. @Controller 사용 가능.
public class HelloControllerTest {
    @Autowired
    private MockMvc mvc;
    // 웹 api 테스트 > 스프링 mvc 테스트의 시작점.

    @Test
    public void isHelloReturned() throws Exception {
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get("/hello")) // MockMvc를 통해 주소로 get 요청 > get()만쓰면 너무 많아서 안나오니 패키지명도 써줌.
                .andExpect(status().isOk()) // 결과검증 : 200 상태인지 http header Status 검증
                .andExpect(content().string(hello)); // 결과검증 : 응답 본문 내용 검증. 리턴값이 맞는 지 확인.
    }

    @Test
    public void isHelloDtoReturned() throws Exception {
        String name = "helloDto";
        int amount = 1557;

        mvc.perform(MockMvcRequestBuilders.get("/hello/dto")
                                                            .param("name",name)
                                                            .param("amount",String.valueOf(amount))) // 숫자/날짜는 String화해서 param에 넣음.
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}
