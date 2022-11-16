package com.example.KangnamShare.service;

import com.example.KangnamShare.dto.CommentDto;
import com.example.KangnamShare.entity.Article;
import com.example.KangnamShare.entity.Comment;
import com.example.KangnamShare.repository.ArticleRepository;
import com.example.KangnamShare.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;




    public List<CommentDto> comments(Long articleId) {
       /* // 조회: 댓글 목록
         List<Comment> comments= commentRepository.findByArticleId(articleId);
        //변환: 엔티티 ->Dto
         List<CommentDto> dtos = new ArrayList<CommentDto>();
         for(int i =0 ;i < comments.size();i++){
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
             dtos.add(dto);
         }
        //반환
        return dtos;*/

        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment->CommentDto.createCommentDto(comment)).collect(Collectors.toList());

    }

    @Transactional
    public CommentDto create(CommentDto dto) {

        //게시글 조회 및 예외 발생
        Article article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(()->new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다."));
        //댓들 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        //댓글 인티티를 DB로 저장
        Comment created = commentRepository.save(comment);
        //DTO로 변경하여 반환
        return CommentDto.createCommentDto(created);

    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto,String username) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 수정 실패! 대상 댓글이 없습니다."));
        //4:잘못된 요청 처리:게시글 작성자랑 수정하려는 사람이 다를 때

        //댓글 수정
        target.patch(dto);
        //DB로 갱신
        Comment updated = commentRepository.save(target);

        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id,String username) {
    //댓글 조회(및 예외 발생)
       Comment target = commentRepository.findById(id).orElseThrow(()->new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다"));

        if (!target.getUsername().equals(username)) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! savedusername: {}, newusername: {}", target.getUsername(), username);
            throw new IllegalArgumentException();
        }
        //댓글 DB에서 삭제
        commentRepository.delete(target);
        //삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
