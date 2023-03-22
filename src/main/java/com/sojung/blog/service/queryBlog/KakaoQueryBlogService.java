package com.sojung.blog.service.queryBlog;

import com.sojung.blog.constant.Constants;
import com.sojung.blog.model.Blog;
import com.sojung.blog.model.queryBlog.KakaoBlogs;
import java.util.List;
import java.util.Optional;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class KakaoQueryBlogService {

    private RestTemplate restTemplate;
    private static final String AUTHORIZATION = "KakaoAK " + Constants.KAKAO_APP_KEY;

    public KakaoQueryBlogService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Blog> getBlogListByKeywordAndPageAndSizeAndTarget(String query, String sort, Integer page, Integer size) {
        String fullUrl = this.createFullUrl(query, sort, page, size);

        return Optional.ofNullable(this.getKakaoBlogs(fullUrl)).map(KakaoBlogs::getBlogs).orElse(null);
    }

    private String createFullUrl(String query, String sort, Integer page, Integer size){
        return Constants.KAKAO_BASE_URL + Constants.KAKAO_BLOG_API_URL + "?query=" + query + "&sort=" + sort+ "&page=" + page + "&size=" + size;
    }

    private KakaoBlogs getKakaoBlogs(String url){
        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.KAKAO_HEADER_KEY, AUTHORIZATION);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<KakaoBlogs> responseEntity;
        try {
            responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<KakaoBlogs>() {
            });
        }catch(HttpClientErrorException e){
            return null;
        }
        return responseEntity.getBody();

    }

}
