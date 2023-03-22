package com.sojung.blog.model.queryBlog;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.sojung.blog.model.Blog;
import java.util.ArrayList;
import java.util.List;

public class KakaoBlogs {
    @JsonProperty("documents")
    private List<Blog> blogs = new ArrayList<>();

    public List<Blog> getBlogs() {
        return blogs;
    }

}
