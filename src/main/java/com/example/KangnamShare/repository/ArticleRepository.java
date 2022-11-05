package com.example.KangnamShare.repository;

import com.example.KangnamShare.entity.Article;
import com.example.KangnamShare.entity.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends CrudRepository<Article,Long> {
    @Override
    ArrayList<Article> findAll();

    @Query(value = "SELECT USERNAME FROM article WHERE ID= :articleId",nativeQuery = true)
    String findUsernameByArticleId(Long articleId);

}
