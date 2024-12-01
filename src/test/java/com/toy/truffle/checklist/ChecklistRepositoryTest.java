package com.toy.truffle.checklist;

import com.toy.truffle.checklist.entity.Checklist;
import com.toy.truffle.checklist.repository.ChecklistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChecklistRepositoryTest {

    @Autowired
    private ChecklistRepository checklistRepository;

    @Test
    @DisplayName("체크리스트 저장")
    public void testSaveChecklist() {
        // given
        //테스트값 세팅
        Checklist checklist = Checklist.builder()
                .travelSeq(1)
                .parentChecklistSeq(null)
                .checklistName("필수준비물")
                .description("대메뉴1")
                .build();

        // when
        // 데이터 저장
        Checklist result = checklistRepository.save(checklist);
        // then
        // 데이터 저장값 검증
        assertThat(checklistRepository).isNotNull();
        assertThat(result.getTravelSeq()).isEqualTo(1);
        assertThat(result.getChecklistName()).isEqualTo("필수준비물");
        assertThat(result.getDescription()).isEqualTo("대메뉴1");
    }

    @Test
    @DisplayName("체크리스트 업데이트")
    public void testUpdateChecklist() {
        // given
        Checklist checklist = Checklist.builder()
                .travelSeq(2)
                .parentChecklistSeq(1)
                .checklistName("필수준비물")
                .description("대메뉴1")
                .build();

        // 저장
        Checklist savedChecklist = checklistRepository.save(checklist);

        // when
        savedChecklist = checklistRepository.findById((long) savedChecklist.getChecklistSeq()).orElseThrow();
        Checklist updatedChecklist = Checklist.builder()
                .checklistSeq(savedChecklist.getChecklistSeq()) // 기존 ID 유지
                .travelSeq(2)
                .parentChecklistSeq(1)
                .checklistName("업데이트된 준비물")
                .description("업데이트된 대메뉴1")
                .build();

        Checklist result = checklistRepository.save(updatedChecklist);

        // then
        assertThat(result.getChecklistSeq()).isEqualTo(savedChecklist.getChecklistSeq());
        assertThat(result.getChecklistName()).isEqualTo("업데이트된 준비물");
        assertThat(result.getDescription()).isEqualTo("업데이트된 대메뉴1");
    }

    @Test
    @DisplayName("체크리스트 삭제")
    public void testDeleteChecklist() {
        // given
        Checklist checklist = Checklist.builder()
                .travelSeq(1)
                .parentChecklistSeq(null)
                .checklistName("삭제테스트명")
                .description("대메뉴1")
                .build();

        // 저장
        Checklist savedChecklist = checklistRepository.save(checklist);

        // when
        checklistRepository.deleteById((long) savedChecklist.getChecklistSeq());

        // then
        assertThat(checklistRepository.findById((long) savedChecklist.getChecklistSeq())).isEmpty();
    }
}
