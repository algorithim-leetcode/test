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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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


    @Test
    @DisplayName("체크리스트 업데이트")
    public void testUpdateChecklist() {
        // given
        ChecklistDto checklistDto = ChecklistDto.builder()
                .checklistSeq(1)
                .travelSeq(1)
                .parentChecklistSeq(null)
                .checklistName("업데이트된 준비물")
                .description("업데이트된 대메뉴1")
                .build();

        Checklist existingChecklist = Checklist.builder()
                .checklistSeq(1)
                .travelSeq(1)
                .parentChecklistSeq(null)
                .checklistName("필수준비물")
                .description("대메뉴1")
                .build();

        // when
        when(checklistRepository.findById(eq(1L))).thenReturn(Optional.of(existingChecklist));
        when(checklistRepository.save(any(Checklist.class))).thenReturn(Checklist.builder()
                .checklistSeq(1)
                .travelSeq(1)
                .parentChecklistSeq(null)
                .checklistName("업데이트된 준비물")
                .description("업데이트된 대메뉴1")
                .build());

        CommonResponseDTO commonResponseDTO = checklistService.updateChecklist(checklistDto);

        // then
        assertTrue(commonResponseDTO.isStatus());
        assertEquals(ResponseStatus.SAVE_SUCCESS.getMessage(), commonResponseDTO.getMessage());
    }

    @Test
    @DisplayName("체크리스트 삭제")
    public void testDeleteChecklist() {
        // given
        Long checklistId = 1L;

        // when
        when(checklistRepository.existsById(checklistId)).thenReturn(true);

        CommonResponseDTO commonResponseDTO = checklistService.deleteChecklist(checklistId);

        // then
        assertTrue(commonResponseDTO.isStatus());
        assertEquals(ResponseStatus.SAVE_SUCCESS.getMessage(), commonResponseDTO.getMessage());
    }
}
