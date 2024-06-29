package com.ict.interviewdotboot.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ict.interviewdotboot.mapper.TossMapper;
import com.ict.interviewdotboot.vo.TossVO;

@Service
public class TossService {
    @Autowired
    private TossMapper tossMapper;

    @Value("${toss.api.url}")
    private String tossApiUrl;

    @Value("${toss.secret.key}")
    private String tossSecretKey;
    
    public boolean confirmPayment(String paymentKey, String orderId, int amount, String authorizationHeader, String id) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            // UTF-8 인코딩 설정 추가
            restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationHeader);
            headers.set("Content-Type", "application/json; charset=UTF-8");
            
            String requestJson = String.format(
                "{\"paymentKey\":\"%s\",\"orderId\":\"%s\",\"amount\":%s,\"userId\":\"%s\"}",
                paymentKey, orderId, amount, id
            );
            
            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
            System.out.println("엔티티 "+entity);

            ResponseEntity<String> response = restTemplate.exchange(
                "https://api.tosspayments.com/v1/payments/confirm",
                HttpMethod.POST,
                entity,
                String.class
            );
            System.out.println("############Response: " + response); // UTF-8로 변환된 응답 출력

             // JSON 응답 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            TossVO tvo = objectMapper.readValue(response.getBody(), TossVO.class);
            if (tvo.getEasyPay() != null) {
                tvo.setAmount(tvo.getEasyPay().getAmount());
            }
            tvo.setId(id);
            System.out.println("파싱된 응답: " + tvo);

            // approvedAt 날짜 변환
            LocalDateTime approvedAt = LocalDateTime.parse(tvo.getApprovedAt(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            String formattedApprovedAt = approvedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            tvo.setApprovedAt(formattedApprovedAt);

            // 이용권 횟수
            tvo.setPayCount(tvo.getAmount()/1000); 

            if (tvo.getAmount() == 1000) {
                tvo.setOrderName("1회 이용권");
            } else if (tvo.getAmount() == 10000) {
                tvo.setOrderName("10회 이용권");
            } else {
                tvo.setOrderName("내맘대로 이용권");
            }

            tvo.setPayStatus("결제완료");

            // DB
            int result = tossMapper.confirmPayment(tvo);
            int result2 = tossMapper.userPayCount(tvo);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            System.out.println("결제오류 : "+e);
        }
        return true;
    }
    
    public boolean cancelPayment(String paymentKey, String t_idx, String authorizationHeader, String id, String cancelReason) {
        try {

            System.out.println("페이먼츠키22"+paymentKey);
            System.out.println("티티티아이디나오낭ㅇㅇㅇ" + t_idx);
            System.out.println("아이디나오낭ㅇㅇㅇ" + id);
            RestTemplate restTemplate = new RestTemplate();
            // UTF-8 인코딩 설정 추가
            restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authorizationHeader);
            headers.set("Content-Type", "application/json; charset=UTF-8");
            
            String requestJson = String.format(
                "{\"paymentKey\":\"%s\",\"t_idx\":\"%s\",\"cancelReason\":\"%s\",\"userId\":\"%s\"}",
                paymentKey, t_idx, cancelReason, id
            );
            
            HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
            System.out.println("엔티티 "+entity);

            ResponseEntity<String> response = restTemplate.exchange(
                "https://api.tosspayments.com/v1/payments/"+paymentKey+"/cancel",
                HttpMethod.POST,
                entity,
                String.class
            );
            System.out.println("############Response: " + response); // UTF-8로 변환된 응답 출력

            // JSON 응답 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            TossVO tvo = objectMapper.readValue(response.getBody(), TossVO.class);
            System.out.println("취추치ㅜ치ㅜ치ㅜ칯파싱된 응답: " + tvo);

            
        if (tvo.getCancels() != null && !tvo.getCancels().isEmpty()) {
            TossVO.Cancel cancel = tvo.getCancels().get(0);
            String canceledAt = cancel.getCanceledAt();
            int cancelAmount = cancel.getCancelAmount();

            LocalDateTime canceledAtDateTime = LocalDateTime.parse(canceledAt, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            String formattedCanceledAt = canceledAtDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            tvo.setCanceledAt(formattedCanceledAt);
            tvo.setCancelAmount(cancelAmount);
            tvo.setPayCount(cancelAmount / 1000);
            tvo.setPayStatus("취소완료");
            tvo.setCancelReason(cancelReason);

            System.out.println("제발제ㅏㅂㄹ"+tvo.getCancelAmount());
            System.out.println("제발제ㅏㅂㄹ"+tvo.getCanceledAt());
            System.out.println("제발제ㅏㅂㄹ"+tvo.getPayCount());
            System.out.println("제발제ㅏㅂㄹ"+tvo.getPayStatus());
            System.out.println("제발제ㅏㅂㄹ"+tvo.getCancelReason());
            int cancelPaymentResult = tossMapper.cancelPayment(tvo);
            int userPayCountResult = tossMapper.userPayCount2(tvo);

            System.out.println("cancelPaymentResult: " + cancelPaymentResult);
            System.out.println("userPayCountResult: " + userPayCountResult);
        }

        } catch (Exception e) {
            System.out.println("취소오류 : "+e);
        }
        return true;
    }
    


    public List<TossVO> userPay(String id) {
        return tossMapper.userPay(id);
    }
}

