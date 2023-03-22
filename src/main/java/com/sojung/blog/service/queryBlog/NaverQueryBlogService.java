package com.sojung.blog.service.queryBlog;

import com.sojung.blog.constant.Constants;
import com.sojung.blog.model.Blog;
import com.sojung.blog.model.queryBlog.NaverBlogs;
import java.util.List;
import java.util.Optional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class NaverQueryBlogService  {

    private RestTemplate restTemplate;

    public NaverQueryBlogService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Blog> getBlogListByKeywordAndPageAndSizeAndTarget(String query,String sort, Integer page, Integer size) {
        String fullUrl = this.createFullUrl(query, sort, page, size);

        return Optional.ofNullable(this.getNaverBlog(fullUrl)).map(NaverBlogs::getBlogs).orElse(null);
    }

    private String createFullUrl(String query, String sort, Integer start, Integer display){
        return Constants.NAVER_BASE_URL + Constants.NAVER_BOOK_API_URL + "?query=" + query + "&sort=" + sort + "&start=" + start + "&display=" + display;
    }

    private NaverBlogs getNaverBlog(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.NAVER_CLIENT_ID_KEY, Constants.NAVER_CLIENT_ID);
        headers.set(Constants.NAVER_CLIENT_SECRET_KEY, Constants.NAVER_CLIENT_SECRET);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        ResponseEntity<NaverBlogs> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<NaverBlogs>() {
                });
        } catch (HttpClientErrorException e) {
            return null;
        }
        return responseEntity.getBody();
    }

}
