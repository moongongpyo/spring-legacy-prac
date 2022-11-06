package com.example.KangnamShare.entity;

import com.example.KangnamShare.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private  String Username;
    @Column
    private String body;
    @ManyToOne// 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다!
    @JoinColumn(name = "article_id")//"article_id" 컬럼에 Atrticle의 대표....
    private Article article;



    public static Comment createComment(CommentDto dto, Article article) {
    //예외처리
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");
        //엔티티 생성 및 반환
        return new Comment(
                 dto.getId(),
                dto.getUsername(),
                dto.getBody(),
                article);

    }

    public void patch(CommentDto dto) {
        //예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");
        if (!this.Username.equals(dto.getUsername())) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! savedusername: {}, newusername: {}", this.Username, dto.getUsername());
            throw new IllegalArgumentException("본인이 작성한 댓글만 수정가능합니다.");
        }
        //객체를 갱신
        if(dto.getUsername()!=null)
            this.Username = dto.getUsername();
        if(dto.getBody()!=null)
            this.body = dto.getBody();
    }
}
