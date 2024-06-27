package com.ict.interviewdotboot.controller;

import com.ict.interviewdotboot.service.TossService;
import com.ict.interviewdotboot.vo.TossVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class TossController {

    @Autowired
    private TossService tossService;
    
    @PostMapping("/confirm")
    public ResponseEntity<TossVO> confirmPayment(@RequestBody TossVO tvo, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            boolean isConfirmed = tossService.confirmPayment(
                tvo.getPaymentKey(),
                tvo.getOrderId(),
                tvo.getAmount(),
                authorizationHeader
                );
            if (isConfirmed) {
                return ResponseEntity.ok(new TossVO());
            } else {
                return ResponseEntity.status(400).body(new TossVO());
            }
        } catch (Exception e) {
            System.out.println("컨트롤러" + e );
        }
        return null;
    }
}
