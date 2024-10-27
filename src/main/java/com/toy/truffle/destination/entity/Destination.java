package com.toy.truffle.destination.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Destination {

    @Id
    private String destinationCd;

    @Column
    private String destinationName;

    @Builder
    public Destination(String destinationCd, String destinationName) {
        this.destinationCd = destinationCd;
        this.destinationName = destinationName;
    }
}
