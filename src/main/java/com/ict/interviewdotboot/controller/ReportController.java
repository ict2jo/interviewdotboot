package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.ReportService;
import com.ict.interviewdotboot.vo.ReportVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {
    
    @Autowired
    private ReportService reportService;

    @GetMapping("/reportlist")
    public List<ReportVO> getReportList() {
        return reportService.getReportList();
    }
    
    @PostMapping("/reportinsert")
    public ResponseEntity<String> insertReport(@RequestBody ReportVO reportVO){
        boolean res = reportService.insertReport(reportVO);
        if(res){
            return ResponseEntity.ok("신고  : Okay");
        }else{
            return ResponseEntity.status(401).body("신고 : NO");
        }
    }
    @PostMapping("/reportupdate")
    public int updateReport(@RequestBody ReportVO reportVO) {
        return reportService.updateReport(reportVO);
    }
    
    
}
