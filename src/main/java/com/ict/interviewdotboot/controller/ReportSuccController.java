package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.ReportSuccService;
import com.ict.interviewdotboot.vo.ReportVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/reportsucc")
public class ReportSuccController {
    
    @Autowired
    private ReportSuccService reportSuccService;

    @GetMapping("/reportlist")
    public List<ReportVO> getReportSuccList() {
        return reportSuccService.getReportSuccList();
    }

    @PostMapping("/reportinsert")
    public ResponseEntity<String> insertSuccReport(@RequestBody ReportVO reportVO) {
        boolean res = reportSuccService.insertSuccReport(reportVO);
        if(res) {
            return ResponseEntity.ok("신고 : okay");
        }else{
            return ResponseEntity.status(401).body("신고 : no");
        }
    }
    
    @PostMapping("/reportupdate")
    public int updateSuccReport(@RequestBody ReportVO reportVO) {
        return reportSuccService.updateSuccReport(reportVO);
    }
    
    
}
