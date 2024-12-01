package com.toy.truffle.checklist.dto;

import com.toy.truffle.checklist.entity.Checklist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ChecklistDto {
    private Integer checklistSeq; // 체크리스트_PK
    private int travelSeq; // 여행_PK
    private Integer parentChecklistSeq; // 부모 체크리스트_PK (JPA 셀프 조인)
    private String checklistName; // 체크리스트 타이틀 혹은 상세
    private String description; // 기본 설명
    private List<ChecklistDto> childChecklists; // 자식 체크리스트 리스트

    public ChecklistDto() {

    }

    // 부모 Checklist를 받아서 엔티티 변환
    public Checklist toEntity(Checklist parentChecklist) {
        return Checklist.builder()
                .travelSeq(this.travelSeq)
                .parentChecklist(parentChecklist)
                .checklistName(this.checklistName)
                .description(this.description)
                .build();
    }
}
