package com.sojung.blog.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sojung.blog.model.Blog;
import java.util.Date;

public class SimpleBlog {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private String dateTime;

    public SimpleBlog(Blog blog){
        this.title = blog.getTitle();
        this.contents = blog.getContents();
        this.url = blog.getUrl();
        this.blogname = blog.getBlogname();
        this.thumbnail = blog.getThumbnail();
        this.dateTime = blog.getDateTime();
    }

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBlogname() {
        return blogname;
    }

    public void setBlogname(String blogname) {
        this.blogname = blogname;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
