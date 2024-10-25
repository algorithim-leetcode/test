package com.toy.truffle.travel.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
    private String createDttm;

    @Temporal(TemporalType.TIMESTAMP)
    private String updateDttm;

    @Builder
    public TravelMain(long travelSeq, String travelTitle, String startDate, String endDate, String createUserId) {
        this.travelSeq = travelSeq;
        this.travelTitle = travelTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createUserId = createUserId;
    }
}
