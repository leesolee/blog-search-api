package com.sojung.blog.service;

import com.sojung.blog.model.PopularKeyword;
import java.util.List;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;

public interface PopularKeywordService {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    void createOrUpdatePopularKeyword(String keyword);

    List<PopularKeyword> getPopularKeywordListOrderByCountDesc();
}
