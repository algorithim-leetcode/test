package com.toy.truffle.checklist;

import com.toy.truffle.checklist.entity.Checklist;
import com.toy.truffle.checklist.repository.ChecklistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
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
}
