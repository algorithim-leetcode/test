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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@DataJpaTest
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
        //테스트 DTO 생성
        ChecklistDto checklistDto = ChecklistDto.builder()
                .travelSeq(101)
                .checklistName("화장품")
                .description("테스트설명")
                .build();

        // when
        // ChecklistService save 호출 예상동작 설정: stub
        when(checklistRepository.save(any(Checklist.class))).thenReturn(Checklist.builder()
                        .checklistSeq(1) // Mock 기본키 설정
                        .travelSeq(101)
                        .checklistName("화장품")
                        .description("테스트설명")
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
        Checklist existingChecklist = Checklist.builder()
                .travelSeq(101)
                .checklistName("화장품")
                .description("테스트설명")
                .build();

        // 저장 후 ID 확인
        Checklist savedChecklist = checklistRepository.save(existingChecklist);
        Integer checklistSeq = savedChecklist.getChecklistSeq(); // 저장된 ID 가져오기

        ChecklistDto checklistDto = ChecklistDto.builder()
                .checklistSeq(checklistSeq) // DTO에 저장된 ID 설정
                .travelSeq(101)
                .checklistName("화장품")
                .description("테스트설명")
                .build();

        // when
        when(checklistRepository.findById(eq(checklistSeq))).thenReturn(Optional.of(savedChecklist));
        CommonResponseDTO commonResponseDTO = checklistService.updateChecklist(checklistDto);

        // then
        assertTrue(commonResponseDTO.isStatus());
        assertEquals(ResponseStatus.SAVE_SUCCESS.getMessage(), commonResponseDTO.getMessage());

        // 데이터 검증
        Checklist updatedChecklist = checklistRepository.findById(checklistSeq)
                .orElseThrow(() -> new AssertionError("체크리스트 업데이트 후 조회 실패"));

        assertEquals(101, updatedChecklist.getTravelSeq());
        assertEquals("화장품", updatedChecklist.getChecklistName());
        assertEquals("테스트설명", updatedChecklist.getDescription());
    }

    @Test
    @DisplayName("체크리스트 삭제")
    public void testDeleteChecklist() {
        // given
        Checklist existingChecklist = Checklist.builder()
                .travelSeq(101)
                .checklistName("화장품")
                .description("테스트설명")
                .build();

        // 저장 후 ID 확인
        Checklist savedChecklist = checklistRepository.save(existingChecklist);
        int checklistSeq = savedChecklist.getChecklistSeq(); // 저장된 ID 가져오기

        // when
        when(checklistRepository.existsById(checklistSeq)).thenReturn(true);

        CommonResponseDTO commonResponseDTO = checklistService.deleteChecklist(checklistSeq);

        // then
        assertTrue(commonResponseDTO.isStatus());
        assertEquals("삭제 성공", commonResponseDTO.getMessage());
    }
}
