package com.example.KangnamShare.api;

import com.example.KangnamShare.dto.ArticleForm;
import com.example.KangnamShare.entity.Article;
import com.example.KangnamShare.repository.ArticleRepository;
import com.example.KangnamShare.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j//로그 추가
@RestController//RestAPI 용 컨트롤러! 데이터(JSON)를 반환
public class ArticleApiController {

    @Autowired//DI, 생성 객체를 가져와 연결!
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);
    }

    //POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created !=null) ? ResponseEntity.status(HttpStatus.OK).body(created) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                         @RequestBody ArticleForm dto){
        Article updated = articleService.update(id,dto);
        return (updated != null) ? ResponseEntity.status(HttpStatus.OK).body(updated) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){

        Article deleted =  articleService.delete(id);

        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    //트랜잭션 ->실패->롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList= articleService.createArticles(dtos);
        return (createdList != null)? ResponseEntity.status(HttpStatus.OK).body(createdList) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build() ;
    }
}
