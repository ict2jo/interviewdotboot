package com.ict.interviewdotboot.controller;

import com.ict.interviewdotboot.service.InterviewService;
import com.ict.interviewdotboot.vo.InterviewVO;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/choose")
    public List<InterviewVO> getQuestionList() {
        return interviewService.getQuestionList();
    }

}
