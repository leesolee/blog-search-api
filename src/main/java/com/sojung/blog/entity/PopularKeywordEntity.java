package com.sojung.blog.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "popular_keywords")
public class PopularKeywordEntity extends BaseEntity{
    @Id
    private String keyword;
    private Long count = 0L;

    public PopularKeywordEntity() {
    }

    public PopularKeywordEntity(String keyword){
        this.setKeyword(keyword);
    }

    public String getKeyword() {
        return keyword;
    }

    public PopularKeywordEntity setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public Long getCount() {
        return count;
    }

}
