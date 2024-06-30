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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentsService commentsService;

    // 면접 후기 댓글
    @GetMapping("/comment")
    public List<CommentVO> getComments(String r_idx) {
        List<CommentVO> list = commentsService.getComments(r_idx);   
        System.out.println(list);
        return list;
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

    @PostMapping("/updatecomment")
    public int updateComment(@RequestBody CommentVO commentVO) {
        return commentsService.updateComment(commentVO);
    }

    @PostMapping("/deletecomment")
    public int deleteComment(@RequestBody CommentVO commentVO) {
        commentVO.setActive("1");
        return commentsService.deleteComment(commentVO);
    }
    
    
}
