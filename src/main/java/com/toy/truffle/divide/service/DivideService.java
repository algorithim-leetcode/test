package com.toy.truffle.divide.service;

import com.toy.truffle.divide.dto.DivideDTO;
import com.toy.truffle.divide.repository.DivideRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class DivideService {

    private static DivideRepository divideRepository;

    public List<DivideDTO> individualExpenses(long travelSeq) {
        List<DivideDTO> repayList = divideRepository.findRepayList(travelSeq);
        List<DivideDTO> paymentList = divideRepository.findPaymentList(travelSeq);

        // userId를 기준으로 Map으로 변환 (userId를 키로 설정)
        Map<String, DivideDTO> resultMap = new HashMap<>();

        // repayList에서 userId를 기준으로 DivideDTO 생성 및 저장
        for (DivideDTO repay : repayList) {
            resultMap.put(repay.getUserId(), DivideDTO.builder()
                    .userId(repay.getUserId())
                    .paidAmount(repay.getPaidAmount()) // paidAmount 설정
                    .totalPayment(0) // 초기값 설정
                    .build());
        }

        // paymentList 처리
        for (DivideDTO payment : paymentList) {
            resultMap.compute(payment.getUserId(), (userId, existingDTO) -> {
                if (existingDTO == null) {
                    // payment만 존재하는 경우 새로운 DTO 생성
                    return DivideDTO.builder()
                            .userId(payment.getUserId())
                            .paidAmount(0) // 초기값 설정
                            .totalPayment(payment.getTotalPayment()) // totalPayment 설정
                            .build();
                } else {
                    // 기존 DTO가 있는 경우 totalPayment 업데이트
                    return existingDTO.toBuilder()
                            .totalPayment(payment.getTotalPayment())
                            .build();
                }
            });
        }

        // Map의 값들을 리스트로 변환하여 반환
        return new ArrayList<>(resultMap.values());
    }
}
