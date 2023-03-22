package com.sojung.blog.model;


import com.sojung.blog.entity.PopularKeywordEntity;

public class PopularKeyword {
    private String keyword;
    private Long count;

    public PopularKeyword(PopularKeywordEntity popularKeywordEntity){
        this.keyword = popularKeywordEntity.getKeyword();
        this.count = popularKeywordEntity.getCount();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
