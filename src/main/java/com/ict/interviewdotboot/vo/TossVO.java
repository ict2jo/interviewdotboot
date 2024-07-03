package com.ict.interviewdotboot.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import java.util.List;

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
    private int cancelAmount;
    private String approvedAt;
    private int payCount;
    private int statusCount;
    private int remainCount;
    private String payStatus;
    private String cancelReason;
    private String paymentKey;
    private String canceledAt;
    private String authorizationHeader;
    private EasyPay easyPay;
    private List<Cancel> cancels;
    private String provider;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EasyPay {
        private String provider;
        private int amount;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cancel {
        private String transactionKey;
        private String cancelReason;
        private int cancelAmount;
        private String canceledAt;
        private int easyPayDiscountAmount;
        private String cancelStatus;
    }
}
