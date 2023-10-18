package com.example.KangnamShare.dto;

import com.example.KangnamShare.entity.Posts;
import lombok.*;

@AllArgsConstructor
@ToString
@Setter

public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private String username;

    private String categories;

    private Long price;
    public Posts toEntity() {
        return new Posts(id,title,content,username,categories,price);
    }



}
