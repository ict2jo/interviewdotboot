package com.ict.interviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.SuccessService;
import com.ict.interviewdotboot.vo.SuccessVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/success")
public class SuccessController {
    @Autowired
    SuccessService successService;

    @GetMapping("/successlist")
    public List<SuccessVO> getSuccessList() {
        return successService.getSuccessList();
    }
    
    @PostMapping("/successdetail")
    public List<SuccessVO> getSuccessDetail() {
        return successService.getSuccessDetail();
    }

    @PostMapping("/successwrite")
    public ResponseEntity<String> insertSuccess(@RequestBody SuccessVO successVO) {
        boolean res = successService.insertSuccess(successVO);
        System.out.println("res : " + res);
        if(res){
            return ResponseEntity.ok("OK1");
        }else{
            return ResponseEntity.status(401).body("OH NO!!!");
        }  
        
    }

    @PostMapping("/updatesuccess")
    public int updateSuccess(@RequestBody SuccessVO successVO) {
        return successService.updateSuccess(successVO);
    }
    @PostMapping("/deletesuccess")
    public int deleteSuccess(@RequestBody SuccessVO successVO) {
        return successService.deleteSuccess(successVO);
    }
}
