package com.sojung.blog.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sojung.blog.repository.PopularKeywordRepository;
import java.util.Arrays;
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
public class PopularKeywordsControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PopularKeywordRepository popularKeywordRepository;

    private final String[] keywords = {"맛집1","맛집2","맛집2","맛집3","맛집3","맛집3"};

    @Test
    @DisplayName("인기 키워드 조회가 되는지 확인한다.")
    public void getPopularKeyword() throws Exception{

        final ResultActions resultActions = mockMvc.perform(get("/popularKeywords")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body", hasSize(0)));
    }

    @Test
    @DisplayName("3개의 키워드를 여러번 입력한 경우, 같은 키워드일 때 카운트만 증가하고 keyword 개수는 3이여야 한다.")
    public void getPopKeywordCount() throws Exception{

        Arrays.stream(keywords).forEach(query -> {
            try {
                mockMvc.perform(get("/blogs")
                    .param("query", query)
                    .contentType(MediaType.APPLICATION_JSON));
            }catch(Exception e){
                e.printStackTrace();
            }
        });
        final ResultActions resultActions = mockMvc.perform(get("/popularKeywords")
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(print());

        resultActions
            .andExpect(status().isOk());

        assertEquals(3, popularKeywordRepository.findAll().size());
    }

}
