package com.toy.truffle.checklist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "checklist")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_seq")
    private int checklistSeq; // 체크리스트_PK

    @Column(name = "travel_seq", nullable = false)
    private int travelSeq; // 여행_PK

    @Column(name = "parent_checklist_seq")
    private Integer parentChecklistSeq; // note : 부모 체크리스트_PK (Integer 타입으로 저장 / null 가능)

//    @ManyToOne
//    @JoinColumn(name = "parent_checklist_seq")
//    private Checklist parentChecklist; // 부모 체크리스트 (셀프 조인)

    @Column(name = "checklist_name", length = 50)
    private String checklistName; // 체크리스트 타이틀 혹은 상세

    @Column(name = "description", length = 100)
    private String description; // 기본 설명

    @Builder
    public Checklist(Integer checklistSeq, int travelSeq, Integer parentChecklistSeq, String checklistName, String description) {
        this.checklistSeq = checklistSeq;
        this.travelSeq = travelSeq;
        this.parentChecklistSeq = parentChecklistSeq;
        this.checklistName = checklistName;
        this.description = description;
    }
}
