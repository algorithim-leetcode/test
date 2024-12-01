package com.toy.truffle.checklist;

import com.toy.truffle.checklist.entity.Checklist;
import com.toy.truffle.checklist.repository.ChecklistRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

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
        Checklist checklist = Checklist.builder()
                .travelSeq(101)
                .checklistName("화장품")
                .description("테스트설명")
                .build();

        // when
        Checklist savedChecklist = checklistRepository.save(checklist);

        // then
        // 저장된 데이터 다시 조회하여 검증
        Checklist result = checklistRepository.findById(savedChecklist.getChecklistSeq())
                .orElseThrow(() -> new AssertionError("체크리스트 저장 후 조회 실패"));

        assertThat(result).isNotNull();
        assertThat(result.getTravelSeq()).isEqualTo(101);
        assertThat(result.getChecklistName()).isEqualTo("화장품");
        assertThat(result.getDescription()).isEqualTo("테스트설명");
    }

    @Test
    @DisplayName("체크리스트 업데이트")
    public void testUpdateChecklist() {
        // given
        Checklist checklist = Checklist.builder()
                .travelSeq(101)
                .checklistName("화장품")
                .description("테스트설명")
                .build();

        // 저장
        Checklist savedChecklist = checklistRepository.save(checklist);

        // 기본키 확인 (여기서 checklistSeq를 확인할 수 있음)
        Integer checklistSeq = savedChecklist.getChecklistSeq();
        assertThat(checklistSeq).isNotNull(); // 기본키가 생성되었는지 검증

        // when
        // 저장된 엔티티 조회 및 업데이트
        Checklist foundChecklist = checklistRepository.findById(checklistSeq)
                .orElseThrow(() -> new AssertionError("체크리스트 조회 실패"));

        // 업데이트 값 설정
        foundChecklist.updateChecklist(
                101, // 새로운 travelSeq
                null, // 부모 체크리스트 없음
                "화장품",
                "테스트설명"
        );

        // then
        Checklist result = checklistRepository.findById(checklistSeq)
                .orElseThrow(() -> new AssertionError("체크리스트 업데이트 후 조회 실패"));

        assertThat(result.getChecklistSeq()).isEqualTo(checklistSeq);
        assertThat(result.getTravelSeq()).isEqualTo(101);
        assertThat(result.getChecklistName()).isEqualTo("화장품");
        assertThat(result.getDescription()).isEqualTo("테스트설명");
    }

    @Test
    @DisplayName("체크리스트 삭제")
    public void testDeleteChecklist() {
        // given
        Checklist checklist = Checklist.builder()
                .travelSeq(101)
                .checklistName("화장품")
                .description("테스트설명")
                .build();

        // 저장
        Checklist savedChecklist = checklistRepository.save(checklist);

        // when
        checklistRepository.deleteById(savedChecklist.getChecklistSeq());

        // then
        Optional<Checklist> deletedChecklist = checklistRepository.findById(savedChecklist.getChecklistSeq());
        assertThat(deletedChecklist).isEmpty(); // 삭제 후 조회 결과가 비어있는지 확인
    }
}
