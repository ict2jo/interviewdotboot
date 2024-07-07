package com.ict.interviewdotboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.OpenaiService;
import com.ict.interviewdotboot.vo.MyuserVO;
import com.ict.interviewdotboot.vo.ResumeVO;
import com.ict.interviewdotboot.vo.TossVO;
import com.ict.interviewdotboot.vo.UserVO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/introduce")
public class OpenaiController {
    @Autowired
    private OpenaiService openaiService;

    @PostMapping("/save")
    public int introduceUpdate(@RequestBody ResumeVO resumeVO) {
        System.out.println("자소서 "+ resumeVO);
        return openaiService.introduceUpdate(resumeVO);
    }

    @PostMapping("/update")
    public int update(@RequestBody MyuserVO myuserVO, String selfIntroduction) {
        myuserVO.setField(myuserVO.getSelfIntroduction());
        return openaiService.update(myuserVO);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody ResumeVO resumeVO) {
        System.out.println("인설트 " + resumeVO);
        return openaiService.insert(resumeVO);
    }

    @GetMapping("/re_select")
    public List<ResumeVO> re_select(@RequestParam("u_idx") String u_idx) {
        System.out.println("셀렉트 " + u_idx);
        return openaiService.re_select(u_idx);
    }

    @GetMapping("/re_select2")
    public List<ResumeVO> re_select2(@RequestParam("resume_idx") String resume_idx) {
        System.out.println("셀렉트2 " + resume_idx);
        return openaiService.re_select2(resume_idx);
    }

    @PostMapping("/re_update")
    public int re_update(@RequestBody ResumeVO resumeVO) {
        System.out.println("업데이트 " + resumeVO);
        return openaiService.re_update(resumeVO);
    }

}
