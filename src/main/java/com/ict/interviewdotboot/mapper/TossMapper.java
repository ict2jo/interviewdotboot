package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.TossVO;

@Mapper
public interface TossMapper {

    int confirmPayment(TossVO tvo);
    int userPayCount(TossVO tvo);

    int cancelPayment(TossVO tvo);
    int userPayCount2(TossVO tvo);
    
    List<TossVO> userPay(String id);
}
