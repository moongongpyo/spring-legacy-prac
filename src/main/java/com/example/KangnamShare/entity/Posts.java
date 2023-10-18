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
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//DB가 id를 자동생성 어노테이션
    private  Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String username;

    @Column
    private String categories;

    @Column Long price;
    public void patch(Posts posts){
        if(posts.title != null)
            this.title = posts.title;
        if(posts.content != null)
            this.content = posts.content;
        if(posts.username != null)
            this.username = posts.username;
        if(posts.categories != null)
            this.categories = posts.categories;
        if(posts.price != null)
            this.price = posts.price;

    }


}
