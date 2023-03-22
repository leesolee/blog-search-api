package com.sojung.blog.controller;

import com.sojung.blog.model.PopularKeyword;
import com.sojung.blog.response.RestResponseEntity;
import com.sojung.blog.service.PopularKeywordService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/popularKeywords")
public class PopularKeywordController {

    private final PopularKeywordService popularKeywordService;

    @Autowired
    public PopularKeywordController(PopularKeywordService popularKeywordService){
        this.popularKeywordService = popularKeywordService;
    }

    @GetMapping
    public RestResponseEntity getPopluarKeywordsBest10(HttpServletRequest request){
        List<PopularKeyword> popularKeywordList = popularKeywordService.getPopularKeywordListOrderByCountDesc();

        return new RestResponseEntity(request, popularKeywordList);
    }

}
