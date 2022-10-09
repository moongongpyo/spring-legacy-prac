package com.example.KangnamShare.service;

import com.example.KangnamShare.dto.ArticleForm;
import com.example.KangnamShare.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest //해당 클래스는 스프링부트와 연동되어 테스팅된다
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {

        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");
        //예상
         List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));
        //실제
        List<Article> articles= articleService.index();
        //비교
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공____존재하는_id_입력() {

        Long id = 1L;
        //예상
        Article expected = new Article(id,"가가가가","1111");
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_실패____존재하지_않는_id_입력 () {

        Long id = -1L;
        //예상
        Article expected = null;
        //실제
        Article article = articleService.show(id);
        //비교
        assertEquals(expected, article);

    }


    @Test
    @Transactional
    void create_성공____title과_content만_있는_dto_입력() {

        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(null,title,content);
        //예상
        Article expected = new Article(4L,title,content);
        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void create_실패____id가_포함된_dto_입력() {

        String title = "라라라라";
        String content = "4444";
        ArticleForm dto = new ArticleForm(4L,title,content);
        //예상
        Article expected = null;
        //실제
        Article article = articleService.create(dto);
        //비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_성공____존재하는_id와_title_content가_있는_dto_입력() {

        String title = "라라라라";
        String content = "4444";
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id,title,content);
        //예상
        Article expected = new Article(id,title,content);
        //실제
        Article article = articleService.update(id,dto);
        //비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void update_성공____존재하는_id와_title만_있는_dto_입력() {

        String title = "라라라라";
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id,title,null);
        //예상
        Article expected = new Article(id,title,"1111");
        //실제
        Article article = articleService.update(id,dto);
        //비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
   @Transactional
    void update_실패____존재하지_않는_id의_dto_입력() {

        String title = "라라라라";
        String content = "4444";
        Long id = -1L;
        ArticleForm dto = new ArticleForm(id,title,content);
        //예상
        Article expected = null;
        //실제
        Article article = articleService.update(id,dto);
        //비교
        assertEquals(expected, article);
    }
    @Test
   @Transactional
    void update_실패____id만_있는_dto_입력() {

        String title = null;
        String content = null;
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id,title,content);
        //예상
        Article expected = new Article(id,"가가가가","1111");
        //실제
        Article article = articleService.update(id,dto);
        //비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void delete_성공____존재하는_id_입력() {
        String title = "가가가가";
        String content = "1111";
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id,title,content);
        //예상
        Article expected = new Article(id,title,content);
        //실제
        Article article = articleService.delete(id);
        //비교
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    @Transactional
    void delete_실패____존재하지_않는_id_입력() {

        Long id = -1L;
        //예상
        Article expected =null;
        //실제
        Article article = articleService.delete(id);
        //비교
        assertEquals(expected, article);
    }

}