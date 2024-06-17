package com.ict.interviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.interviewdotboot.vo.GuestVO;
import com.ict.interviewdotboot.vo.MembersVO;

import java.util.List;

@Mapper
public interface MemberMapper {
    MembersVO selectMember(@Param("id") String id) ;
    List<GuestVO> getGuestList();
    List<MembersVO> getAdminList();
}
