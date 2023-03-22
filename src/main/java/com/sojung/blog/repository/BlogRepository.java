package com.sojung.blog.repository;

import com.sojung.blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogEntity,String> {

}
