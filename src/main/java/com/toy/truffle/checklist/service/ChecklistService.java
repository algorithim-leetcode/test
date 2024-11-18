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

        // 기존 체크리스트가 있는지 확인
        Checklist checklist = checklistRepository.findById((long) checklistDto.getChecklistSeq())
                .orElse(null);

        if (checklist == null) {
            return CommonResponseDTO.builder()
                    .status(false)
                    .message("업데이트할 체크리스트를 찾을 수 없습니다.")
                    .build();
        }

        // DTO 데이터를 엔티티에 직접 반영
        // 기존 ID를 유지하면서 새로운 값을 가진 엔티티 생성
        Checklist updatedChecklist = Checklist.builder()
                .checklistSeq(checklistDto.getChecklistSeq()) // 기존 ID 유지
                .travelSeq(checklistDto.getTravelSeq())
                .parentChecklistSeq(checklistDto.getParentChecklistSeq())
                .checklistName(checklistDto.getChecklistName())
                .description(checklistDto.getDescription())
                .build();

        checklistRepository.save(updatedChecklist);

        return CommonResponseDTO.builder()
                .status(true)
                .message("업데이트 성공")
                .build();
    }

    /**
     * 체크리스트 삭제
     */
    @Transactional
    public CommonResponseDTO deleteChecklist(Long id) {
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
