package com.ict.interviewdotboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ict.interviewdotboot.vo.JobTestVO;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class WebController {
  private static final String Q_URL = "https://www.career.go.kr/inspct/openapi/test";
   @PostMapping("/report")
    public ResponseEntity<?> handleReport(@RequestBody JobTestVO data) {
      System.out.println("Received data: " + data);
      // 외부 API로 데이터 전송
        RestTemplate restTemplate = new RestTemplate();
        String requestUrl = Q_URL + "/report?apikey=" + "631411887293319c018c3eeeb7413e40" + "&questrnSeq=" + "19";

        ResponseEntity<String> response = restTemplate.postForEntity(requestUrl, data, String.class);
        
        return ResponseEntity.ok(response.getBody());
    }
}
