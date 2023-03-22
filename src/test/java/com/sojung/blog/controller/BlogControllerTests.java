package com.sojung.blog.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sojung.blog.repository.PopularKeywordRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BlogControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PopularKeywordRepository popularKeywordRepository;

    @Test
    @DisplayName("키워드를 한번 검색한 경우 결과는 10개이고, 인기검색어도 1개 저장되어야 한다.")
    public void defaultSearchKeyword() throws Exception{

        String query = "맛집";

        final ResultActions resultActions = mockMvc.perform(get("/blogs")
                .param("query",query)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.body", hasSize(10)));

        assertEquals(1, popularKeywordRepository.findAll().size());
    }

    @Test
    @DisplayName("블로그 검색 요청 키워드가 null 값인 경우 에러가 발생한다.")
    public void emptyKeywordException() throws Exception{
        String keyword = "";

        final ResultActions resultActions = mockMvc.perform(get("/blogs")
                .param("keyword", keyword)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("블로그 검색 요청 페이지 수가 50초과인 경우 에러가 발생한다.")
    public void pageOverMaxException() throws Exception{
        String keyword = "test";
        String page = "101";

        final ResultActions resultActions = mockMvc.perform(get("/blogs")
                .param("keyword", keyword)
                .param("page", page)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("블로그 검색 요청 페이지 수가 1미만인 경우 에러가 발생한다.")
    public void pageUnderMinException() throws Exception{
        String keyword = "test";
        String page = "0";

        final ResultActions resultActions = mockMvc.perform(get("/blogs")
                .param("keyword", keyword)
                .param("page", page)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("블로그 검색 요청 문서 수가 50초과인 경우 에러가 발생한다.")
    public void sizeOverMaxException() throws Exception{
        String keyword = "test";
        String size = "51";

        final ResultActions resultActions = mockMvc.perform(get("/blogs")
                .param("keyword", keyword)
                .param("size", size)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("블로그 검색 요청 문서 수가 10미만인 경우 에러가 발생한다.")
    public void sizeUnderMinNumberException() throws Exception{
        String keyword = "test";
        String size = "0";

        final ResultActions resultActions = mockMvc.perform(get("/blogs")
                .param("keyword", keyword)
                .param("size", size)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isBadRequest());
    }
}
