package com.example.KangnamShare.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식 가능(해당 클래스로 테이블을 만든다)
@AllArgsConstructor
@NoArgsConstructor//디폴트 생성자 추가
@ToString
@Getter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 id를 자동생성 어노테이션
    private  Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String username;
    public void patch(Article article){
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
        if(article.username != null)
            this.username = article.username;

    }


}
