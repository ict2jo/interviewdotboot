package com.ict.interviewdotboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.OpenaiService;
import com.ict.interviewdotboot.vo.MyuserVO;
import com.ict.interviewdotboot.vo.ResumeVO;
import com.ict.interviewdotboot.vo.UserVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/introduce")
public class OpenaiController {
    @Autowired
    private OpenaiService openaiService;

    @PostMapping("/save")
    public int introduceUpdate(@RequestBody UserVO uvo, String correctedEssay) {
        uvo.setField(uvo.getCorrectedEssay());
        return openaiService.introduceUpdate(uvo);
    }

    @PostMapping("/update")
    public int update(@RequestBody MyuserVO myuserVO, String selfIntroduction) {
        myuserVO.setField(myuserVO.getSelfIntroduction());
        return openaiService.update(myuserVO);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ResumeVO resumeVO) {
        System.out.println("히히 " + resumeVO);
        return openaiService.insert(resumeVO);
    }

}
