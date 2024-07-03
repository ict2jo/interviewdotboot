package com.ict.interviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ict.interviewdotboot.mapper.MypageMapper;
import com.ict.interviewdotboot.vo.CalendarVO;
import com.ict.interviewdotboot.vo.MyinquiryVO;
import com.ict.interviewdotboot.vo.MyuserVO;

@Service
public class MypageService {
    @Autowired
    private MypageMapper mypageMapper;

    public List<MyinquiryVO> getinquiry(String id){
        return mypageMapper.getinquiry(id);
    }
    
    public int insertinquiry(MyinquiryVO myinquiryVO){
        return mypageMapper.insertinquiry(myinquiryVO);
    }

    public int editinquiry(MyinquiryVO myinquiryVO){
        return mypageMapper.editinquiry(myinquiryVO);
    }
    
    public List<MyinquiryVO> getInquiryDetail(String i_idx){
        return mypageMapper.getInquiryDetail(i_idx);
    }
    public int deleteinquiry(String i_idx){
        return mypageMapper.deleteinquiry(i_idx);
    }
    public List<MyuserVO> getprofile(MyuserVO myuserVO){
        return mypageMapper.getprofile(myuserVO);
    }
    public int insertprofile(String u_idx){
        return mypageMapper.insertprofile(u_idx);
    }
    @Transactional
    public int editprofile(MyuserVO myuserVO) {
        int result1 = mypageMapper.editprofile1(myuserVO);
        int result2 = mypageMapper.editprofile2(myuserVO);
        
        if (result1 > 0 && result2 > 0) {
            return 1;
        } else {
            throw new RuntimeException("데이터베이스 업데이트 실패"); 
        }
    }

    public int addfavorites(MyuserVO myuserVO){
        System.out.println("fdsfdsf젠장");
        return mypageMapper.addfavorites(myuserVO);
    }
    public int deletefavorites(MyuserVO myuserVO){
        return mypageMapper.deletefavorites(myuserVO);
    }
    public List<MyuserVO> getstar( String u_idx){
        return mypageMapper.getstar(u_idx);
    }
    public List<CalendarVO> selectCalendar( CalendarVO calendarVO){
        return mypageMapper.selectCalendar(calendarVO);
    }
    public int insertCalendar(CalendarVO calendarVO) {
    return mypageMapper.insertCalendar(calendarVO);
    }
    public int updateCalendar(CalendarVO calendarVO) {
        System.out.println(calendarVO.getC_idx());
    return mypageMapper.updateCalendar(calendarVO);
    }
    public int deleteCalendar(CalendarVO calendarVO) {
    return mypageMapper.deleteCalendar(calendarVO);
    }
}
