package com.toy.truffle.checklist.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_checklist_seq") // 부모의 외래 키
    private Checklist parentChecklist;

    @OneToMany(mappedBy = "parentChecklist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Checklist> childChecklists; // 자식 체크리스트 리스트

    @Column(name = "checklist_name", length = 50)
    private String checklistName; // 체크리스트 타이틀 혹은 상세

    @Column(name = "description", length = 100)
    private String description; // 기본 설명

    @Builder
    public Checklist(Integer checklistSeq, Integer travelSeq, Checklist parentChecklist, String checklistName, String description) {
        this.checklistSeq = checklistSeq;
        this.travelSeq = travelSeq;
        this.parentChecklist = parentChecklist;
        this.checklistName = checklistName;
        this.description = description;
    }

    @Builder
    public Checklist(Integer travelSeq, Checklist parentChecklist, String checklistName, String description) {
        this.travelSeq = travelSeq;
        this.parentChecklist = parentChecklist;
        this.checklistName = checklistName;
        this.description = description;
    }
    /**
     * 엔티티 업데이트 메서드
     */
    public void updateChecklist(Integer travelSeq, Checklist parentChecklist, String checklistName, String description) {
        this.travelSeq = travelSeq;
        this.parentChecklist = parentChecklist;
        this.checklistName = checklistName;
        this.description = description;
    }
}
