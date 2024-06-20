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




@RestController
@RequestMapping("/mypage")
public class MypageController {
    
    @Autowired
    private MypageService mypageService;

    @GetMapping("/inquiry")
    public List<MyinquiryVO> getinquiry() {
        return mypageService.getinquiry();
    }

    @PostMapping("/inquirywrite")
    public int insertinquiry(@RequestBody MyinquiryVO myinquiryVO) {
        return mypageService.insertinquiry(myinquiryVO);
    }
    @GetMapping("/selfprofile")
    public List<MyuserVO> getprofile() {
        return mypageService.getprofile();
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
    public List<MyuserVO> getstar() {
        return mypageService.getstar();
    }
}
