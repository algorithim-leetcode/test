package com.toy.truffle.destination.entity;

import com.toy.truffle.travel.entity.TrvlDstnMapping;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "destination")
    private List<TrvlDstnMapping> trvlDstnMapping = new ArrayList<>();

    @Builder
    public Destination(String destinationCd, String destinationName) {
        this.destinationCd = destinationCd;
        this.destinationName = destinationName;
    }
}
