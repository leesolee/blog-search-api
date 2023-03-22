package com.sojung.blog.service;

import com.sojung.blog.entity.BlogEntity;
import com.sojung.blog.model.Blog;
import com.sojung.blog.model.response.SimpleBlog;
import com.sojung.blog.repository.BlogRepository;
import com.sojung.blog.service.queryBlog.KakaoQueryBlogService;
import com.sojung.blog.service.queryBlog.NaverQueryBlogService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService{

    private final KakaoQueryBlogService kakaoQueryBlogService;
    private final NaverQueryBlogService naverQueryBlogService;
    private final PopularKeywordService popularKeywordService;
    private final BlogRepository blogRepository;

    @Autowired
    public BlogServiceImpl(
        @Qualifier("kakaoQueryBlogService") KakaoQueryBlogService kakaoQueryBlogService,
        @Qualifier("naverQueryBlogService") NaverQueryBlogService naverQueryBlogService,
        PopularKeywordService popularKeywordService,
        BlogRepository blogRepository) {
        this.kakaoQueryBlogService = kakaoQueryBlogService;
        this.naverQueryBlogService = naverQueryBlogService;
        this.popularKeywordService = popularKeywordService;
        this.blogRepository = blogRepository;
    }

    @Override
    public List<SimpleBlog> getSimpleBookListByKeywordAndPageAndSizeAndTarget(String query, String sort,
        Integer page, Integer size) {
        List<Blog> blogList = Optional.ofNullable(kakaoQueryBlogService.getBlogListByKeywordAndPageAndSizeAndTarget(query, sort, page, size))
            .orElseGet(()->naverQueryBlogService.getBlogListByKeywordAndPageAndSizeAndTarget(query, sort, page, size));

        popularKeywordService.createOrUpdatePopularKeyword(query);

        // caching
        blogRepository.saveAll(blogList.stream().map(BlogEntity::new).collect(Collectors.toList()));

        return blogList.stream().map(SimpleBlog::new).collect(Collectors.toList());
    }
}
