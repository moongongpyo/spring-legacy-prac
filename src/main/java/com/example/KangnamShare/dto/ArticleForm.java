package com.example.KangnamShare.dto;

import com.example.KangnamShare.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@ToString
@Setter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    public Article toEntity() {
        return new Article(id,title,content);
    }
}
