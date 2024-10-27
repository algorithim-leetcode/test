package com.toy.truffle.checklist;

import com.toy.truffle.checklist.dto.ChecklistDto;
import com.toy.truffle.checklist.entity.Checklist;
import com.toy.truffle.checklist.repository.ChecklistRepository;
import com.toy.truffle.checklist.service.ChecklistService;
import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ChecklistServiceTest {
    @InjectMocks
    private ChecklistService checklistService;

    @Mock
    private ChecklistRepository checklistRepository;

    @Test
    @DisplayName("체크리스트 저장")
    public void testSaveChecklist() {
        // given

        //테스트 DTO 생성
        ChecklistDto checklistDto = ChecklistDto.builder()
                .travelSeq(1)
                .parentChecklistSeq(null)
                .checklistName("필수준비물")
                .description("대메뉴1")
                .build();

        // when
        // ChecklistService save 호출 예상동작 설정: stub
        when(checklistRepository.save(any(Checklist.class))).thenReturn(Checklist.builder()
                        .travelSeq(1)
                        .parentChecklistSeq(null)
                        .checklistName("필수준비물")
                        .description("대메뉴1")
                        .build());

        // 체크리스트  저장 로직 호출
        CommonResponseDTO commonResponseDTO  = checklistService.saveChecklist(checklistDto);

        // then
        // 데이터 저장 상태값 검증
        assertTrue(commonResponseDTO.isStatus());
        // 메시지 내용 검증
        assertEquals(ResponseStatus.SAVE_SUCCESS.getMessage(), commonResponseDTO.getMessage());
    }
}
