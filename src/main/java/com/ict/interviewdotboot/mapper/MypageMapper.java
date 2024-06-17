package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.MypageVO;

@Mapper
public interface MypageMapper {
    List<MypageVO> getinquiry();
}
