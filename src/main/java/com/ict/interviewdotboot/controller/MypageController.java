package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.MypageService;
import com.ict.interviewdotboot.vo.MyinquiryVO;
import com.ict.interviewdotboot.vo.MyuserVO;

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
    public List<MyinquiryVO> getinquiry() {
        return mypageService.getinquiry();
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
    public int editinquiry(@RequestBody MyinquiryVO myinquiryVO) {
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

    @GetMapping("/star")
    public List<MyuserVO> getstar(@RequestParam("u_idx") String u_idx) {
        return mypageService.getstar(u_idx);
    }

    
}
