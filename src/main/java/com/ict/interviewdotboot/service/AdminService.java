package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.MemberMapper;
import com.ict.interviewdotboot.vo.MembersVO;

import java.util.List;


@Service
public class AdminService {
     @Autowired
    private MemberMapper memberMapper;

    public List<MembersVO> getAdminList(){
        return memberMapper.getAdminList();
    }
}
