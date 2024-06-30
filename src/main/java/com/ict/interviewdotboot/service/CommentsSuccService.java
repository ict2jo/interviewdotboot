package com.ict.interviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.CommentsSuccMapper;
import com.ict.interviewdotboot.vo.CommentsSuccVO;

@Service
public class CommentsSuccService {
    
    @Autowired
    private CommentsSuccMapper commentsSuccMapper;

    public List<CommentsSuccVO> getComments(String s_idx) {
        return commentsSuccMapper.getComments(s_idx);
    } 

    /* public List<CommentVO> getComments(String r_idx) {
        return commentsMapper.getComments(r_idx);
    } */

    public boolean insertComment(CommentsSuccVO commentsSuccVO){
        int res = commentsSuccMapper.insertComment(commentsSuccVO);
        return res > 0;
    }

    public int updateComment(CommentsSuccVO commentsSuccVO){
        return commentsSuccMapper.updateComment(commentsSuccVO);
    }

    public int deleteComment(CommentsSuccVO commentsSuccVO){
        return commentsSuccMapper.deleteComment(commentsSuccVO);
    }
}
