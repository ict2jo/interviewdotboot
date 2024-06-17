package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.MypageService;
import com.ict.interviewdotboot.vo.MypageVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/mypage")
public class MypageController {
    
    @Autowired
    private MypageService mypageService;

    @GetMapping("/inquiry")
    public List<MypageVO> getinquiry() {
        System.out.println("왔나");
        return mypageService.getinquiry();
    }
    
}
