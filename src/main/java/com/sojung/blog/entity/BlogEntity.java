package com.sojung.blog.entity;

import com.sojung.blog.model.Blog;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BlogCache")
public class BlogEntity extends BaseEntity{
    @Id
    private String url;
    private String title;
    private String contents;
    private String blogname;
    private String thumbnail;
    private String dateTime;

    public BlogEntity() {

    }

    public BlogEntity(Blog blog){
        this.setUrl(blog.getUrl());
        this.setTitle(blog.getTitle());
        this.setContents(blog.getContents());
        this.setBlogname(blog.getBlogname());
        this.setThumbnail(blog.getThumbnail());
        this.setDateTime(blog.getDateTime());
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
