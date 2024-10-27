package com.toy.truffle.travel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TravelMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long travelSeq;

    @Column
    private String travelTitle;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String startDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private String createUserId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false)
    private String createDttm;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(insertable = false)
    private String updateDttm;

    //TODO :: updateDttm insert할때도 값이 들어가는 문제 해결 필요

    @Builder
    public TravelMain(long travelSeq, String travelTitle, String startDate, String endDate, String createUserId) {
        this.travelSeq = travelSeq;
        this.travelTitle = travelTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createUserId = createUserId;
    }
}
