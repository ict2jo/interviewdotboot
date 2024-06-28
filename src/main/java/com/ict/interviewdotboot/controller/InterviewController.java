package com.ict.interviewdotboot.controller;

import com.ict.interviewdotboot.service.InterviewService;
import com.ict.interviewdotboot.vo.InterviewVO;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional  // 컨트롤러 메서드에서 트랜잭션을 직접 관리합니다.
    public ResponseEntity<String> insertResults(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody Map<String, Object> requestBody) {

        String id = (String) requestBody.get("id");
        List<Map<String, Object>> results = (List<Map<String, Object>>) requestBody.get("results");

        InterviewVO interviewVO = new InterviewVO();
        interviewVO.setId(id);

        // Interview_t 테이블에 레코드 삽입
        interviewService.insertTable(interviewVO);

        // 방금 삽입된 Interview_t의 r_idx 값을 가져옵니다.
        String r_idx = interviewVO.getR_idx();

        // 3개의 Interview_re 레코드를 삽입
        for (Map<String, Object> result : results) {
            InterviewVO resultVO = new InterviewVO();
            resultVO.setR_idx(r_idx);
            resultVO.setQ_idx(String.valueOf(result.get("q_idx")));
            resultVO.setId(interviewVO.getId());
            resultVO.setText((String) result.get("text"));
            resultVO.setPose_results((String) result.get("pose_results"));
            resultVO.setSentiment((String) result.get("sentiment"));
            interviewService.insertResult(resultVO);
        }

        return ResponseEntity.ok("DB에 저장 완료.");
    }

    @GetMapping("/history")
    public List<InterviewVO> getInterviewHistory(InterviewVO interviewVO){
        return  interviewService.getInterviewHistory(interviewVO);
    }

    @GetMapping("/historydetail")
    public List<InterviewVO> getHistoryDetail(InterviewVO interviewVO){
        return interviewService.getHistoryDetail(interviewVO);
    }


}
