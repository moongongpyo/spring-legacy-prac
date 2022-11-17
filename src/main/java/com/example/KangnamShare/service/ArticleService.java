package com.example.KangnamShare.service;

import com.example.KangnamShare.dto.ArticleDTO;
import com.example.KangnamShare.entity.Article;
import com.example.KangnamShare.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service //서비스 선언!(서비스 객체를 스프링 부트에 선언)
public class ArticleService {
    @Autowired //DI
    private ArticleRepository articleRepository;

    public List<Article> index() {

        return articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleDTO dto) {
        Article article = dto.toEntity();
        if(article.getId() != null)
            return null;
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleDTO dto) {
        // 1: DTO -> 엔티티
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());
        // 2: 타겟 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3: 잘못된 요청 처리:json이 비어있거나 아이디가 다를 때
        if (target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return null;
        }
        //4:잘못된 요청 처리:게시글 작성자랑 수정하려는 사람이 다를 때
        if (!articleRepository.findUsernameByArticleId(id).equals(article.getUsername())) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! username: {}, article: {}", articleRepository.findUsernameByArticleId(id), article.toString());
            return null;
        }
        // 4: 업데이트
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id, String username) {
        //대상찾기
        Article target = articleRepository.findById(id).orElse(null);


        //잘못된 요청 처리:json이 비어있을 때
        if (target == null)
            return null;

        //4:잘못된 요청 처리:게시글 작성자랑 삭제하려는 사람이 다를 때
        if (!articleRepository.findUsernameByArticleId(id).equals(username)) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! savedUsername: {}, newUsername: {}", articleRepository.findUsernameByArticleId(id),username);
            return null;
        }
        //대상 삭제
        articleRepository.delete(target);
        return target;
    }

    @Transactional//해당 메소드를 트랜잭션으로 묶는다!
    public List<Article> createArticles(List<ArticleDTO> dtos) {
        //dto 묶음을 entity 묶음으로 변환
        List<Article> articleList=dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        //entity 묶음을 DB로 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));
        //강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                ()->new IllegalArgumentException("결제실패!")
        );

        //결과값 반환
        return articleList;
    }
}
