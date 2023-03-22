package com.sojung.blog.repository;

import com.sojung.blog.entity.PopularKeywordEntity;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PopularKeywordRepository extends JpaRepository<PopularKeywordEntity, String> {

    List<PopularKeywordEntity> findAllByOrderByCountDesc();

    // for popularKeywords.count's concurrency problem
    @Modifying
    @Transactional
    @Query("UPDATE PopularKeywordEntity p SET p.count = p.count + 1L WHERE p.keyword = :keyword")
    Integer increaseCount(@Param("keyword") String keyword);
}
