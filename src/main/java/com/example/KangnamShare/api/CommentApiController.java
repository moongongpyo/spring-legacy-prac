package com.example.KangnamShare.api;

import com.example.KangnamShare.annotation.RunningTime;
import com.example.KangnamShare.dto.CommentDto;
import com.example.KangnamShare.service.CommentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    //댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable("articleId") Long articleId){
        //서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }
    //댓글 생성
    @PostMapping("/api/articles/comments")
    public ResponseEntity <CommentDto> create(@RequestBody CommentDto dto){
        //서비스에게 위임
        CommentDto createdDto = commentService.create(dto);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }
    //댓글 수정
    @PatchMapping("/api/comments")
    public ResponseEntity <CommentDto> update(@RequestBody CommentDto dto){
        //서비스에게 위임
        CommentDto updatedDto = commentService.update(dto.getId(), dto,dto.getUsername());
        //결과 응답
        return (updatedDto != null) ? ResponseEntity.status(HttpStatus.OK).body(updatedDto) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    //댓글 삭제
    @RunningTime
    @DeleteMapping("/api/comments/{id}/{username}")
    public ResponseEntity <CommentDto> delete(@PathVariable("id") Long id,@PathVariable String username){
        //서비스에게 위임
        CommentDto deletedDto = commentService.delete(id,username);
        //결과 응답

        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
