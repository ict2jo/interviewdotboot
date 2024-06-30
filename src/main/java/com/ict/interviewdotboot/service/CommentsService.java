package com.ict.interviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.CommentsMapper;
import com.ict.interviewdotboot.vo.CommentVO;

@Service
public class CommentsService {
    
    @Autowired
    private CommentsMapper commentsMapper;

    public List<CommentVO> getComments(String r_idx) {
        return commentsMapper.getComments(r_idx);
    } 

    /* public List<CommentVO> getComments(String r_idx) {
        return commentsMapper.getComments(r_idx);
    } */

    public boolean insertComment(CommentVO commentVO){
        int res = commentsMapper.insertComment(commentVO);
        return res > 0;
    }

    public int updateComment(CommentVO commentVO){
        return commentsMapper.updateComment(commentVO);
    }
}
