package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.CommentsService;
import com.ict.interviewdotboot.vo.CommentVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentsService commentsService;

   @GetMapping("/comment")
    public List<CommentVO> getComments() {
        return commentsService.getComments();
    } 

    @PostMapping("/postcomment")
    public ResponseEntity<String> insertComment(@RequestBody CommentVO commentsVO) {
        System.out.println(commentsVO);
        boolean res2 = commentsService.insertComment(commentsVO);
        System.out.println("res2 : " + res2);
        if(res2){
            return ResponseEntity.ok("답글 OK");
        }else{
            return ResponseEntity.status(401).body("답글 NO");
        }
    }
}
