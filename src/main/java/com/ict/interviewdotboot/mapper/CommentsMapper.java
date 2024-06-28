package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestBody;

import com.ict.interviewdotboot.vo.CommentVO;

@Mapper
public interface CommentsMapper {
    List<CommentVO> getComments();
    int insertComment(CommentVO commentsVO);
}
