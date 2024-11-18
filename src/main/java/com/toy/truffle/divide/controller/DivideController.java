package com.toy.truffle.divide.controller;

import com.toy.truffle.divide.dto.DivideDTO;
import com.toy.truffle.divide.service.DivideService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/divide")
public class DivideController {

    /**
     * 정산
     * */

    // TODO : (1) 개인별 지출금액 (2) 누가 누구에게 얼마를?

    /**
     * payment_member : expense_seq, travel_seq, price
     * expense        : expense_seq, travel_seq, user_id, payment_yn, repay_yn
     * */

    private final DivideService divideService;

    /**
     * 개인별 지출금액, 결제금액 조회
     * */
    public void individualExpenses(@RequestParam("travelSeq") long travelSeq, Model model) {
        // travel_seq(여행PK)로 묶어서 user_id(사용자ID)별 결제/정산금액 분류
        List<DivideDTO> totalList = divideService.individualExpenses(travelSeq);
        model.addAttribute("totalList", totalList);
    }





}
