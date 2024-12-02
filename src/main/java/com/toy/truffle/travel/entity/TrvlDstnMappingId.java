package com.toy.truffle.travel.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TrvlDstnMappingId implements Serializable {

    private long travelSeq;
    private String destinationCd;

}
