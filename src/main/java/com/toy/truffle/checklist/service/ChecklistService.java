package com.toy.truffle.checklist.service;

import com.toy.truffle.checklist.dto.ChecklistDto;
import com.toy.truffle.checklist.entity.Checklist;
import com.toy.truffle.checklist.repository.ChecklistRepository;
import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import groovy.util.logging.Slf4j;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChecklistService {
    private final ChecklistRepository checklistRepository;

    /**
     * 체크리스트 저장
     */
    @Transactional
    public CommonResponseDTO saveChecklist(ChecklistDto checklistDto) {
        Checklist checklist = checklistDto.toEntity();

        Checklist result = checklistRepository.save(checklist);

        ResponseStatus responseStatus = (result != null)
                ? ResponseStatus.SAVE_SUCCESS
                : ResponseStatus.SAVE_FAILURE;

        return CommonResponseDTO.builder()
                .status(responseStatus.isStatus())
                .message(responseStatus.getMessage())
                .build();
    }
}
