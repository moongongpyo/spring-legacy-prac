package com.example.KangnamShare.dto;

import com.example.KangnamShare.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id;
    private String username;
    private String body;
    @JsonProperty("article_id")//json 에서 날아오는 변수와 파라메터로 설정한 변수의 이름이 다를때
    private Long articleId;

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(

                comment.getId(),
                comment.getUsername(),
                comment.getBody(),
                comment.getPosts().getId()

        );

    }

}
