package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.MypageVO;
import com.ict.interviewdotboot.vo.MyuserVO;

@Mapper
public interface MypageMapper {
    List<MypageVO> getinquiry();
    int insertinquiry(MypageVO mypageVO);
    List<MyuserVO> getprofile();
    int editprofile1(MyuserVO myuserVO);
    int editprofile2(MyuserVO myuserVO);

}
