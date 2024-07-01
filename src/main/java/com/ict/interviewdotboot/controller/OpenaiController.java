package com.ict.interviewdotboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.OpenaiService;
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
    


}
