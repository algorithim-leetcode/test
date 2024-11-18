package com.toy.truffle.checklist.controller;

import com.toy.truffle.checklist.dto.ChecklistDto;
import com.toy.truffle.checklist.service.ChecklistService;
import com.toy.truffle.global.dto.CommonResponseDTO;
import com.toy.truffle.travel.dto.TravelDto;
import com.toy.truffle.travel.service.TravelService;
import com.toy.truffle.user.dto.SignUpDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/checklist")
public class ChecklistController {

    private final ChecklistService checklistService;

//    // 체크리스트 페이지 호출
//    @GetMapping("/info")
//    public String checklistInfo(Model model) {
//        model.addAttribute("checklistDTO", new ChecklistDto());
//        return "common/checklist";
//    }

    // 체크리스트 저장
    @PostMapping("/saveChecklist")
    @ResponseBody
    public CommonResponseDTO saveChecklist(@RequestBody ChecklistDto ChecklistDto) {
        return checklistService.saveChecklist(ChecklistDto);
    }

    // 체크리스트 업데이트
    @PutMapping("/updateChecklist")
    @ResponseBody
    public CommonResponseDTO updateChecklist(@RequestBody ChecklistDto checklistDto) {
        return checklistService.updateChecklist(checklistDto);
    }

    // 체크리스트 삭제
    @DeleteMapping("/deleteChecklist/{id}")
    @ResponseBody
    public CommonResponseDTO deleteChecklist(@PathVariable Long id) {
        return checklistService.deleteChecklist(id);
    }


}
