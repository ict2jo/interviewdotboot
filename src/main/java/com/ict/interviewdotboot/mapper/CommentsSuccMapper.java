package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.CommentsSuccVO;

@Mapper
public interface CommentsSuccMapper {
    List<CommentsSuccVO> getComments(String s_idx);
    int insertComment(CommentsSuccVO commentsSuccVO);
    int updateComment(CommentsSuccVO commentsSuccVO);
    int deleteComment(CommentsSuccVO commentsSuccVO);
}
