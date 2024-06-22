package com.ict.interviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.interviewdotboot.mapper.MypageMapper;
import com.ict.interviewdotboot.vo.MyinquiryVO;
import com.ict.interviewdotboot.vo.MyuserVO;

@Service
public class MypageService {
    @Autowired
    private MypageMapper mypageMapper;

    public List<MyinquiryVO> getinquiry(){
        return mypageMapper.getinquiry();
    }

    public int insertinquiry(MyinquiryVO myinquiryVO){
        return mypageMapper.insertinquiry(myinquiryVO);
    }

    public List<MyuserVO> getprofile(MyuserVO myuserVO){
        return mypageMapper.getprofile(myuserVO);
    }

    @Transactional
    public int editprofile(MyuserVO myuserVO) {
        int result1 = mypageMapper.editprofile1(myuserVO);
        int result2 = mypageMapper.editprofile2(myuserVO);
        
        if (result1 > 0 && result2 > 0) {
            return 1;
        } else {
            throw new RuntimeException("데이터베이스 업데이트 실패"); // 실패 시 롤백을 위해 예외 던지기
        }
    }

    public int addfavorites(MyuserVO myuserVO){
        System.out.println("fdsfdsf젠장");
        return mypageMapper.addfavorites(myuserVO);
    }
    public int deletefavorites(MyuserVO myuserVO){
        return mypageMapper.deletefavorites(myuserVO);
    }
    public List<MyuserVO> getstar(){
        return mypageMapper.getstar();
    }
}
