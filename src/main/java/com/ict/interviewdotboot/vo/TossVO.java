package com.ict.interviewdotboot.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TossVO {
    private String t_idx;
    private String u_idx;
    private String id;
    private String orderId;
    private String orderName;
    private int amount;
    private int totalAmount;
    private String approvedAt;
    private String dueDate;
    private String paymentKey;
    private String authorizationHeader;
    private EasyPay easyPay;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EasyPay {
        @JsonProperty("amount")
        private int amount;
    }
}
