package com.ict.interviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.UserVO;

@Mapper
public interface OpenaiMapper {

    int introduceUpdate(UserVO uvo);
    
}
