package com.ict.interviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.ReviewMapper;
import com.ict.interviewdotboot.vo.CommentVO;
import com.ict.interviewdotboot.vo.ReviewVO;

@Service
public class ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    

    public List<ReviewVO> getReviewList(){
        return reviewMapper.getReviewList();
    }

    public List<ReviewVO> getReviewDetail(){
        return reviewMapper.getReviewDetail();
    }

    /* public int insertReview(ReviewVO reviewVO){
        System.out.println(reviewVO);
        return reviewMapper.insertReview(reviewVO);
    } */

    public boolean insertReview(ReviewVO reviewVO){
        int res = reviewMapper.insertReview(reviewVO);
        return res > 0;
    }

    public int updateReview(ReviewVO reviewVO){
        return reviewMapper.updateReview(reviewVO);
    }

    public int deleteReview(ReviewVO reviewVO){
        return reviewMapper.deleteReview(reviewVO);
    }

    public List<CommentVO> getComments(){
        return reviewMapper.getComments();
    }

    public boolean insertComment(CommentVO commentsVO){
        int res2 = reviewMapper.insertComment(commentsVO);
        return res2 > 0;
    }
}
