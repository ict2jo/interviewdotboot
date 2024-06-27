package com.ict.interviewdotboot.controller;

import com.ict.interviewdotboot.service.InterviewService;
import com.ict.interviewdotboot.vo.InterviewVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> insertResults(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        String id = (String) requestBody.get("id");
        List<Map<String, Object>> results = (List<Map<String, Object>>) requestBody.get("results");

        for (Map<String, Object> result : results) {
            InterviewVO interviewVO = new InterviewVO();
            interviewVO.setId(id);
            interviewVO.setQuestion((String) result.get("question"));
            interviewVO.setQ_idx(String.valueOf(result.get("q_idx")));
            interviewVO.setText((String) result.get("text"));
            interviewVO.setPose_results((String) result.get("pose_results"));
            interviewVO.setSentiment((String) result.get("sentiment"));

            interviewService.insertResult(interviewVO);
            interviewService.insertTable(interviewVO);
        }

        return ResponseEntity.ok("DB에 저장 완료.");
    }
}
