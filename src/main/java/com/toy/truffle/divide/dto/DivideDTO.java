package com.toy.truffle.divide.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class DivideDTO {
    private String userId;          // 사용자ID
    private Integer paidAmount;     // 개인별 결제금액
    private Integer totalPayment;   // 개인별 정산금액
}
