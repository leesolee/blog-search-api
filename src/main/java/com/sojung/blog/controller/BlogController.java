package com.sojung.blog.controller;

import com.sojung.blog.model.response.SimpleBlog;
import com.sojung.blog.response.RestResponseEntity;
import com.sojung.blog.service.BlogService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blogs")
@Validated
public class BlogController {

    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public RestResponseEntity getBooks(
        @RequestParam(value = "query") @Valid @NotBlank String query,
        @RequestParam(value = "sort", required = false) @Valid String sort,
        @RequestParam(value = "page", required = false, defaultValue = "1") @Valid @Min(1) @Max(50) Integer page,
        @RequestParam(value = "size", required = false, defaultValue = "10") @Valid @Min(1) @Max(50) Integer size,
        HttpServletRequest request) {
        List<SimpleBlog> SimpleBookList = blogService.getSimpleBookListByKeywordAndPageAndSizeAndTarget(
            query, sort, page, size);

        return new RestResponseEntity(request, SimpleBookList);
    }
}
