package com.toy.truffle.travel.entity;

import com.toy.truffle.destination.entity.Destination;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "destination_mapping")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrvlDstnMapping {
    // 기본키 매핑
    @EmbeddedId
    private TrvlDstnMappingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("destinationCd") // trvlDstnMappingKey의 destinationCd와 매핑
    @JoinColumn(name = "destination_cd", nullable = false)
    private Destination destination;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("travelSeq") // trvlDstnMappingKey의 travelSeq와 매핑
    @JoinColumn(name = "travel_seq", nullable = false)
    private TravelMain travelMain;

    @Builder
    public TrvlDstnMapping(TrvlDstnMappingId id, Destination destination, TravelMain travelMain) {
        this.id = id;
        this.destination = destination;
        this.travelMain = travelMain;
    }

}
