package com.sojung.blog.service;

import com.sojung.blog.model.response.SimpleBlog;
import java.util.List;

public interface BlogService {

    List<SimpleBlog> getSimpleBookListByKeywordAndPageAndSizeAndTarget(String keyword, String sort, Integer page, Integer size);
}
