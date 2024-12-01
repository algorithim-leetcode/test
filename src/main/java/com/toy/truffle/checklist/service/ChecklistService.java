package com.toy.truffle.checklist.service;

import com.toy.truffle.checklist.dto.ChecklistDto;
import com.toy.truffle.checklist.entity.Checklist;
import com.toy.truffle.checklist.repository.ChecklistRepository;
import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import groovy.util.logging.Slf4j;
import jakarta.persistence.EntityNotFoundException;
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
        Checklist parentChecklist = null;

        // 부모 Checklist 조회
        if (checklistDto.getParentChecklistSeq() != null) {
            parentChecklist = checklistRepository.findById(checklistDto.getParentChecklistSeq())
                    .orElseThrow(() -> new EntityNotFoundException("Parent Checklist not found with id: " + checklistDto.getParentChecklistSeq()));
        }

        Checklist checklist = checklistDto.toEntity(parentChecklist);
        Checklist result = checklistRepository.save(checklist);

        ResponseStatus responseStatus = (result != null)
                ? ResponseStatus.SAVE_SUCCESS
                : ResponseStatus.SAVE_FAILURE;

        return CommonResponseDTO.builder()
                .status(responseStatus.isStatus())
                .message(responseStatus.getMessage())
                .build();
    }

    /**
     * 체크리스트 업데이트
     */
    @Transactional
    public CommonResponseDTO updateChecklist(ChecklistDto checklistDto) {
        if (checklistDto.getChecklistSeq() == null) {
            return CommonResponseDTO.builder()
                    .status(false)
                    .message("ID가 필요합니다.")
                    .build();
        }

        // 기존 체크리스트 조회
        Checklist checklist = checklistRepository.findById(checklistDto.getChecklistSeq())
                .orElse(null);

        if (checklist == null) {
            return CommonResponseDTO.builder()
                    .status(false)
                    .message("업데이트할 체크리스트를 찾을 수 없습니다.")
                    .build();
        }

        // 부모 체크리스트 설정
        Checklist parentChecklist = null;
        if (checklistDto.getParentChecklistSeq() != null) {
            parentChecklist = checklistRepository.findById(checklistDto.getParentChecklistSeq())
                    .orElseThrow(() -> new EntityNotFoundException("Parent Checklist not found with id: " + checklistDto.getParentChecklistSeq()));
        }

        // 변경 데이터 반영 (dirty checking 활용)
        checklist.updateChecklist(
                checklistDto.getTravelSeq(),
                parentChecklist,
                checklistDto.getChecklistName(),
                checklistDto.getDescription()
        );

        return CommonResponseDTO.builder()
                .status(true)
                .message("업데이트 성공")
                .build();
    }

    /**
     * 체크리스트 삭제
     */
    @Transactional
    public CommonResponseDTO deleteChecklist(int id) {
        if (!checklistRepository.existsById(id)) {
            return CommonResponseDTO.builder()
                    .status(false)
                    .message("삭제할 체크리스트를 찾을 수 없습니다.")
                    .build();
        }

        checklistRepository.deleteById(id);
        return CommonResponseDTO.builder()
                .status(true)
                .message("삭제 성공")
                .build();
    }
}
