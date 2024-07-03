package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.MypageService;
import com.ict.interviewdotboot.vo.CalendarVO;
import com.ict.interviewdotboot.vo.MyinquiryVO;
import com.ict.interviewdotboot.vo.MyuserVO;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/mypage")
public class MypageController {
    
    @Autowired
    private MypageService mypageService;

    // 1:1문의
    @GetMapping("/inquiry")
    public List<MyinquiryVO> getinquiry(@RequestParam("id") String id) {
        return mypageService.getinquiry(id);
    }

    @GetMapping("/inquirydetail")
    public List<MyinquiryVO> getInquiryDetail(@RequestParam("i_idx") String i_idx) {
        return mypageService.getInquiryDetail(i_idx);
    }

    @PostMapping("/inquirywrite")
    public int insertinquiry(@RequestBody MyinquiryVO myinquiryVO) {
        return mypageService.insertinquiry(myinquiryVO);
    }
    @PostMapping("/inquiryedit")
    public int editinquiry(MyinquiryVO myinquiryVO) {
        return mypageService.editinquiry(myinquiryVO);
    }
    
    @PostMapping("/inquirydelete")
    public int deleteinquiry(@RequestParam("i_idx") String i_idx) {
        return mypageService.deleteinquiry(i_idx);
    }
    //마이페이지
    @GetMapping("/selfprofile")
    public List<MyuserVO> getprofile(MyuserVO myuserVO) {
        return mypageService.getprofile(myuserVO);
    }
    //마이페이지
    @PostMapping("/selfprofileinsert")
    public int insertprofile(@RequestParam("u_idx") String u_idx) {
        return mypageService.insertprofile(u_idx);
    }

    @PostMapping("/editprofile")
    public int editprofile(@RequestBody MyuserVO myuserVO) {
        return mypageService.editprofile(myuserVO);
    }

    @PostMapping("/favorites")
    public int addfavorites(@RequestBody MyuserVO myuserVO) {
        return mypageService.addfavorites(myuserVO);
    }
    
    @PostMapping("/nonefavorites")
    public int deletefavorites(@RequestBody MyuserVO myuserVO) {
        return mypageService.deletefavorites(myuserVO);
    }

    @GetMapping("/getstar")
    public List<MyuserVO> getstar(@RequestParam("u_idx") String u_idx) {
        return mypageService.getstar(u_idx);
    }

    @GetMapping("/selectCalendar")
    public List<CalendarVO> selectCalendar(@RequestParam("u_idx") String u_idx) {
        CalendarVO calendarVO = new CalendarVO();
        calendarVO.setU_idx(u_idx);
    return mypageService.selectCalendar(calendarVO);
    }
    @PostMapping("/insertCalendar")
    public int insertCalendar(@RequestBody CalendarVO calendarVO) {
    return mypageService.insertCalendar(calendarVO);
    }
    @PostMapping("/updateCalendar")
    public int updateCalendar(@RequestBody CalendarVO calendarVO) {
    return mypageService.updateCalendar(calendarVO);
    }
    @PostMapping("/deleteCalendar")
    public int deleteCalendar(@RequestBody CalendarVO calendarVO) {
    return mypageService.deleteCalendar(calendarVO);
    }
}
