package com.sojung.blog.model.queryBlog;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NaverBlog {
    private String title;
    @JsonProperty("description")
    private String contents;
    @JsonProperty("bloggername")
    private String blogname;
    @JsonProperty("link")
    private String url;
    @JsonProperty("postdate")
    private String dateTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getBlogname() {
        return blogname;
    }

    public void setBlogname(String blogname) {
        this.blogname = blogname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
