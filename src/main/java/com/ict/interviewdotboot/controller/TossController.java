package com.ict.interviewdotboot.controller;

import com.ict.interviewdotboot.service.TossService;
import com.ict.interviewdotboot.vo.TossVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class TossController {

    @Autowired
    private TossService tossService;

    @PostMapping("/success")
    public ResponseEntity<?> handlePaymentResult(Model model,
            @RequestParam("orderId") String orderId, // 클라이언트에서 전달된 주문 ID
            @RequestParam("amount") String amount, // 클라이언트에서 전달된 결제 금액
            @RequestParam("paymentKey") String paymentKey,
            @RequestBody TossVO tossVO) {
        try {
            tossService.savePayment(tossVO);
            return ResponseEntity.ok().body("Payment successfully processed.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Payment processing failed: " + e.getMessage());
        }
    }
}
