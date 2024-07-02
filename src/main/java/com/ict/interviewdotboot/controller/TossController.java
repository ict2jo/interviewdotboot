package com.ict.interviewdotboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.interviewdotboot.service.TossService;
import com.ict.interviewdotboot.vo.SuccessVO;
import com.ict.interviewdotboot.vo.TossVO;


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
                authorizationHeader,
                tvo.getId()
                );
            if (isConfirmed) {
                System.out.println("헤더"+authorizationHeader);
                System.out.println("아이디디디디디디디디: " + tvo.getId());
                return ResponseEntity.ok(new TossVO());
            } else {
                return ResponseEntity.status(400).body(new TossVO());
            }
        } catch (Exception e) {
            System.out.println("컨트롤러" + e );
        }
        return null;
    }

    // @PostMapping("/cancel")
    // public ResponseEntity<TossVO> cancelPayment(@RequestBody TossVO tvo, @RequestHeader("Authorization") String authorizationHeader) {
    //     try {
    //         System.out.println("티아이디"+tvo.getT_idx());
    //         System.out.println("페이먼츠키"+tvo.getPaymentKey());
    //         System.out.println("z키킼키키" + authorizationHeader);
    //         boolean isCanceled = tossService.cancelPayment(
    //             tvo.getPaymentKey(),
    //             tvo.getT_idx(),
    //             authorizationHeader,
    //             tvo.getId(),
    //             tvo.getCancelReason()
    //             );
    //         if (isCanceled) {
    //             return ResponseEntity.ok(new TossVO());
    //         } else {
    //             return ResponseEntity.status(400).body(new TossVO());
    //         }
    //     } catch (Exception e) {
    //         System.out.println("컨트롤러" + e );
    //     }
    //     return null;
    // }


    @PostMapping("/cancel")
    public int cancelPayment(@RequestBody TossVO tvo) {
        return tossService.cancelPayment(tvo);
    }

    @GetMapping("/userPay")
    public List<TossVO> userPay(@RequestParam("id") String id) {
        System.out.println("오니니니니니니니니니");
        return tossService.userPay(id);
    }

}
