package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.CommentVO;

@Mapper
public interface CommentsMapper {
    List<CommentVO> getComments(String r_idx);
    /* List<CommentVO> getComments(String r_idx); */
    int insertComment(CommentVO commentsVO);
    int updateComment(CommentVO commentVO);
}
