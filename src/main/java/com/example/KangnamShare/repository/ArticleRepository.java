package com.example.KangnamShare.repository;

import com.example.KangnamShare.entity.Article;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    ArrayList<Article> findAll(Sort id);

    @Query(value = "SELECT USERNAME FROM article WHERE ID= :articleId",nativeQuery = true)
    String findUsernameByArticleId(Long articleId);

}
