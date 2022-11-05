package com.example.KangnamShare.repository;

import antlr.collections.impl.LList;
import com.example.KangnamShare.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository  extends JpaRepository<Comment,Long> {
    //특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM  comment WHERE article_id = :articleId",nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    //특정 닉네임의 모든 댓글 조회
   // List<Comment> findByNickname(@Param("nickname") String nickname);


    @Query(value = "SELECT USERNAME FROM comment WHERE id= :id",nativeQuery = true)
    String findUsernameByCommentId(@Param("id")Long id);
}
