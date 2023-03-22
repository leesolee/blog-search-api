package com.sojung.blog.service;

import com.sojung.blog.entity.PopularKeywordEntity;
import com.sojung.blog.model.PopularKeyword;
import com.sojung.blog.repository.PopularKeywordRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PopularKeywordServiceImpl implements PopularKeywordService {
    private final PopularKeywordRepository popularKeywordRepository;

    @Autowired
    public PopularKeywordServiceImpl(PopularKeywordRepository popularKeywordRepository){
        this.popularKeywordRepository = popularKeywordRepository;
    }

    @Override
    public void createOrUpdatePopularKeyword(String keyword){
        popularKeywordRepository.findById(keyword).orElseGet(() -> popularKeywordRepository.save(new PopularKeywordEntity(keyword)));
        popularKeywordRepository.increaseCount(keyword);

    }

    @Override
    public List<PopularKeyword> getPopularKeywordListOrderByCountDesc(){
        return popularKeywordRepository.findAllByOrderByCountDesc()
                .stream()
                .map(PopularKeyword::new)
                .collect(Collectors.toList());
    }
}
