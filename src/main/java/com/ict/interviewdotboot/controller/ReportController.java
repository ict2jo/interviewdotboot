package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.ReportService;
import com.ict.interviewdotboot.vo.ReportVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/report")
public class ReportController {
    
    @Autowired
    private ReportService reportService;

    @PostMapping("/reportreview")
    public int reportReview(@RequestBody ReportVO reportVO){
        return reportService.reportReview(reportVO);
    }
    
}
