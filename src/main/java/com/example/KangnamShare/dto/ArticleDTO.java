package com.example.KangnamShare.dto;

import com.example.KangnamShare.entity.Article;
import lombok.*;

@AllArgsConstructor
@ToString
@Setter

public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String username;
    public Article toEntity() {
        return new Article(id,title,content,username);
    }



}
