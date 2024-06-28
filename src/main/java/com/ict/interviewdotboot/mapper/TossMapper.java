package com.ict.interviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.TossVO;

@Mapper
public interface TossMapper {

    int confirmPayment(TossVO tvo);
    
}
