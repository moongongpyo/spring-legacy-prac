package com.example.KangnamShare.controller;


import com.example.KangnamShare.dto.ArticleDTO;
import com.example.KangnamShare.dto.CommentDto;
import com.example.KangnamShare.entity.Posts;
import com.example.KangnamShare.repository.ArticleRepository;
import com.example.KangnamShare.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j//로깅을 위한 어노테이션
public class ArticleController {
    @Autowired//스프링 부트가 미리 생성해놓은 객체를 가져다가 자동연결
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }
    @PostMapping("/articles/create")
    public String createArticle(ArticleDTO form){
        log.info(form.toString());
        //System.out.println(form.toString());  ->로깅기능을 대체

        //1.Dto를 Entity로 변환
        Posts posts =form.toEntity();
        log.info(posts.toString());

        //2.Repository에게 Entity를 저장하게함
        Posts saved = articleRepository.save(posts);
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        //url요청을 parameter로 받아올 때에는PathVariable 어노테이션을 사용한다
        log.info("id = "+id);
        //1:아 이디로데이터를 가져옴
        Posts postsEntity = articleRepository.findById(id).orElse(null);
         List<CommentDto> commentDtos= commentService.comments(id);
        //2:가져온 데이터를 모델에 등록!
        model.addAttribute("article", postsEntity);
        model.addAttribute("commentDtos",commentDtos);
        //3:보여줄 페이지를 설정
        return "articles/show";
    }
    @GetMapping("/articles")
    public String index(Model model){
        //1:모든 Article을 가져온다!
        List<Posts> postsEntityList =articleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        //2:가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList", postsEntityList);
        //3:뷰 페이지를 설정!
        return "articles/index";
    }
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id ,Model model){
        // 수정할 데이터를 가져오기!
        Posts postsEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터를 등록!
        model.addAttribute("article", postsEntity);
        //뷰 페이지 설정
        return "articles/edit";
    }
    @PostMapping("articles/update")
    public String update(ArticleDTO form){
        log.info(form.toString());

        //1:Dto를 엔티티로 변환
        Posts postsEntity =  form.toEntity();
        //2:엔티티를 DB로 저장한다
        //2-1 DB에서 기존 데이터를 가져온다
        Posts target =   articleRepository.findById(postsEntity.getId()).orElse(null);
        //2-2 기존 데이터가 있다면 값을 갱신한다
        if(target != null){
            articleRepository.save(postsEntity);
        }
        //3:수정 결과 페이지로 리다이렉트 한다
        return "redirect:/articles/"+ postsEntity.getId();
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable  Long id, RedirectAttributes rttr){
        log.info("삭제요청이 들어왔습니다.");
        //1: 삭제 대상을 가져온다
        Posts target=articleRepository.findById(id).orElse(null);
        //2: 대상을 삭제한다
        if(target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다");
        }
        //3: 결과 페이지로 리다이렉트한다
        return "redirect:/articles";
    }
}
