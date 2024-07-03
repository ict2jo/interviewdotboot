package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.CommentsSuccService;
import com.ict.interviewdotboot.vo.CommentsSuccVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/commentsucc")
public class SuccCommentController {
    
    @Autowired
    private CommentsSuccService commentsSuccService;

    @GetMapping("/comment")
    public List<CommentsSuccVO> getComments(String s_idx) {
        List<CommentsSuccVO> list = commentsSuccService.getComments(s_idx);
        return list;
    }
    
    @PostMapping("/postcomment")
    public ResponseEntity<String> insertComment(@RequestBody CommentsSuccVO commentsSuccVO) {
        System.out.println(commentsSuccVO);
        boolean res = commentsSuccService.insertComment(commentsSuccVO);
        System.out.println("res : " + res);
        if(res){
            return ResponseEntity.ok("댓글  OK");
        }else{
            return ResponseEntity.status(401).body("댓글 NO");
        }
        
    }

    @PostMapping("/updatecomment")
    public int updateComment(@RequestBody CommentsSuccVO commentsSuccVO) {
        return commentsSuccService.updateComment(commentsSuccVO);
    }

    @PostMapping("/deletecomment")
    public int deleteComment(@RequestBody CommentsSuccVO commentsSuccVO) {
        commentsSuccVO.setActive("1");
        return commentsSuccService.deleteComment(commentsSuccVO);
    }
    
    
    
}
