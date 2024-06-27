package com.ict.interviewdotboot.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Controller // 이 클래스는 웹 컨트롤러로 사용됩니다.
@RequestMapping(value="/") // 기본 경로를 지정합니다.
public class Toss2Controller {

    // 결제가 성공했을 때 호출되는 메소드입니다.
    @GetMapping(value = "success")
    public String paymentResult(
            Model model,
            @RequestParam(value = "orderId") String orderId, // 클라이언트에서 전달된 주문 ID
            @RequestParam(value = "amount") Integer amount, // 클라이언트에서 전달된 결제 금액
            @RequestParam(value = "paymentKey") String paymentKey) throws Exception { // 클라이언트에서 전달된 결제 키

        // 토스 페이먼츠 API 사용을 위한 시크릿 키입니다.
        String secretKey = "test_sk_6bJXmgo28ewgnlNybNGMVLAnGKWx:";

        // 시크릿 키를 Base64 형식으로 인코딩합니다.
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
        String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

        // 토스 페이먼츠 API의 URL을 설정합니다.
        URL url = new URL("https://api.tosspayments.com/v1/payments/" + paymentKey);

        // HTTP 연결을 설정합니다.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", authorizations); // 인가 헤더 설정
        connection.setRequestProperty("Content-Type", "application/json"); // Content-Type 설정
        connection.setRequestMethod("POST"); // POST 요청 설정
        connection.setDoOutput(true); // 출력 스트림 사용 설정

        // 요청 본문에 포함할 JSON 객체를 생성합니다.
        JSONObject obj = new JSONObject();
        obj.put("orderId", orderId);
        obj.put("amount", amount);

        // 요청 본문을 서버에 전송합니다.
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(obj.toString().getBytes("UTF-8"));

        // 서버의 응답 코드를 확인합니다.
        int code = connection.getResponseCode();
        boolean isSuccess = code == 200; // 응답 코드가 200이면 성공으로 간주합니다.
        model.addAttribute("isSuccess", isSuccess);

        // 서버의 응답을 읽어들입니다.
        InputStream responseStream = isSuccess ? connection.getInputStream() : connection.getErrorStream();
        Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(reader);
        responseStream.close();
        
        // 응답 데이터를 모델에 추가합니다.
        model.addAttribute("responseStr", jsonObject.toJSONString());
        System.out.println(jsonObject.toJSONString());

        model.addAttribute("method", (String) jsonObject.get("method"));
        model.addAttribute("orderName", (String) jsonObject.get("orderName"));

        // 결제 방법에 따라 추가 정보를 모델에 추가합니다.
        if (((String) jsonObject.get("method")) != null) {
            switch ((String) jsonObject.get("method")) {
                case "카드":
                    model.addAttribute("cardNumber", (String) ((JSONObject) jsonObject.get("card")).get("number"));
                    break;
                case "가상계좌":
                    model.addAttribute("accountNumber", (String) ((JSONObject) jsonObject.get("virtualAccount")).get("accountNumber"));
                    break;
                case "계좌이체":
                    model.addAttribute("bank", (String) ((JSONObject) jsonObject.get("transfer")).get("bank"));
                    break;
                case "휴대폰":
                    model.addAttribute("customerMobilePhone", (String) ((JSONObject) jsonObject.get("mobilePhone")).get("customerMobilePhone"));
                    break;
            }
        } else {
            model.addAttribute("code", (String) jsonObject.get("code"));
            model.addAttribute("message", (String) jsonObject.get("message"));
        }

        // 성공 페이지를 반환합니다.
        return "success";
    }

    // 결제가 실패했을 때 호출되는 메소드입니다.
    @GetMapping(value = "fail")
    public String paymentResult(
            Model model,
            @RequestParam(value = "message") String message, // 클라이언트에서 전달된 에러 메시지
            @RequestParam(value = "code") Integer code // 클라이언트에서 전달된 에러 코드
    ) throws Exception {

        // 에러 정보를 모델에 추가합니다.
        model.addAttribute("code", code);
        model.addAttribute("message", message);

        // 실패 페이지를 반환합니다.
        return "fail";
    }

}
