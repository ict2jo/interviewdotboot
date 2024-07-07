package com.ict.interviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.interviewdotboot.vo.MyuserVO;
import com.ict.interviewdotboot.vo.ResumeVO;
import com.ict.interviewdotboot.vo.UserVO;

@Mapper
public interface OpenaiMapper {
    int introduceUpdate(ResumeVO resumeVO);
    int update1(MyuserVO myuserVO);
    int update2(MyuserVO myuserVO);
    int insert(ResumeVO resumeVO);

    List<ResumeVO> re_select(String u_idx);
    List<ResumeVO> re_select2(String resume_idx);
    int re_update(ResumeVO resumeVO);
}
