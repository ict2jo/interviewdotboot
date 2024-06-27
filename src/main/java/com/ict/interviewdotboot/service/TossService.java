package com.ict.interviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.interviewdotboot.mapper.TossMapper;
import com.ict.interviewdotboot.vo.TossVO;

@Service
public class TossService {
    @Autowired
    private TossMapper tossMapper;

    public int savePayment(TossVO tossVO) {
        return tossMapper.savePayment(tossVO);
    }
}
