package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.MyinquiryVO;
import com.ict.interviewdotboot.vo.MyuserVO;

@Mapper
public interface MypageMapper {
    List<MyinquiryVO> getinquiry();
    int insertinquiry(MyinquiryVO myinquiryVO);
    List<MyuserVO> getprofile();
    int editprofile1(MyuserVO myuserVO);
    int editprofile2(MyuserVO myuserVO);
    int addfavorites(MyuserVO myuserVO);
    int deletefavorites(MyuserVO myuserVO);
    List<MyuserVO> getstar();
}
