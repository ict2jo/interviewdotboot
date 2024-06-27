package com.ict.interviewdotboot.controller;

import com.ict.interviewdotboot.service.InterviewService;
import com.ict.interviewdotboot.vo.InterviewVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @GetMapping("/choose")
    public List<InterviewVO> getQuestionList(InterviewVO interviewVO) {
        return interviewService.getQuestionList(interviewVO);
    }

    @PostMapping("/finish")
    public ResponseEntity<String> insertResults(@RequestBody List<InterviewVO> interviewVOList) {
        for (InterviewVO interviewVO : interviewVOList) {
            interviewService.insertResult(interviewVO);
        }
        return ResponseEntity.ok("DB에 저장 완료.");
    }

}
