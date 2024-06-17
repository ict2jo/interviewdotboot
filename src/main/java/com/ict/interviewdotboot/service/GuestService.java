package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.MemberMapper;
import com.ict.interviewdotboot.vo.GuestVO;

import java.util.List;

@Service
public class GuestService {
    @Autowired
    private MemberMapper memberMapper;

    public List<GuestVO> getGuestList(){
        return memberMapper.getGuestList();
    }
}
