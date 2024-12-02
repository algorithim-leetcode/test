package com.toy.truffle.travel.dto;

import com.toy.truffle.travel.entity.TravelMain;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder(toBuilder = true)
public class TravelDto {
    private long travelSeq;
    private String travelTitle;
    private LocalDate startDate;
    private LocalDate endDate;
    private String createUserId;

    public TravelMain toEntity() {
        return TravelMain.builder()
                .travelSeq(travelSeq)
                .travelTitle(travelTitle)
                .startDate(startDate)
                .endDate(endDate)
                .createUserId(createUserId)
                .build();
    }
}
