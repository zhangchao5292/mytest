package com.example.mytest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Author zhangchao
 * @Date 2021/8/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MytestApplication.class})
public class ApplicationTests {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void postTest() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.post("/ok/b")
                .content("{\"age\":\"2\",\"name\":\"测试\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultActions action = mvc.perform(rb);
        action.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        System.out.println(action.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void getTest() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/ok/a")
                .param("name", "jack")
                .param("age", "12")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE);
        ResultActions action = mvc.perform(rb);
        action.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        System.out.println(action.andReturn().getResponse().getContentAsString());
    }


}
