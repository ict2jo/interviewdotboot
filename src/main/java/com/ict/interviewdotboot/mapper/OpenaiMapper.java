package com.ict.interviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.MyuserVO;
import com.ict.interviewdotboot.vo.ResumeVO;
import com.ict.interviewdotboot.vo.UserVO;

@Mapper
public interface OpenaiMapper {
    int introduceUpdate(UserVO uvo);
    int update1(MyuserVO myuserVO);
    int update2(MyuserVO myuserVO);
    int insert(ResumeVO resumeVO);
}
