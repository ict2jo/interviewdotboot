package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.CommentVO;
import com.ict.interviewdotboot.vo.ReviewVO;

@Mapper
public interface ReviewMapper {
    List<ReviewVO> getReviewList();
    List<ReviewVO> getReviewDetail();
    int insertReview(ReviewVO reviewVO);    
    int updateReview(ReviewVO reviewVO);
    int deleteReview(ReviewVO reviewVO);
    /* List<CommentVO> getComments();
    int insertComment(CommentVO commentsVO); */
}
