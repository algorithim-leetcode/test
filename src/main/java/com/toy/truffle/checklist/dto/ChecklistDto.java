package com.toy.truffle.checklist.dto;

import com.toy.truffle.checklist.entity.Checklist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
public class ChecklistDto {
    private Integer checklistSeq; // 체크리스트_PK
    private int travelSeq; // 여행_PK
    private Integer parentChecklistSeq; // 부모 체크리스트_PK (JPA 셀프 조인)
    private String checklistName; // 체크리스트 타이틀 혹은 상세
    private String description; // 기본 설명

    public ChecklistDto() {

    }

    public Checklist toEntity() {
        return Checklist.builder()
                .checklistSeq(checklistSeq)
                .travelSeq(travelSeq)
                .parentChecklistSeq(parentChecklistSeq)
                .checklistName(checklistName)
                .description(description)
                .build();
    }
}
