package com.toy.truffle.travel.service;

import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import com.toy.truffle.travel.dto.TravelDto;
import com.toy.truffle.travel.entity.TravelMain;
import com.toy.truffle.travel.repository.TravelMainRepository;
import groovy.util.logging.Slf4j;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TravelService {
    private final TravelMainRepository travelMainRepository;

    /**
     * 여행 저장
     */
    @Transactional
    public CommonResponseDTO saveTravel(TravelDto travelDto) {
        TravelMain travelMain = travelDto.toEntity();

        TravelMain result = travelMainRepository.save(travelMain);

        // TODO : 여행스타일 저장로직 추가 필요
        // TODO : travelDto - createUserId 로그인 정보로 가져오도록 추가 필요
        
        ResponseStatus responseStatus = (result != null)
                ? ResponseStatus.TRAVEL_SAVE_SUCCESS
                : ResponseStatus.TRAVEL_SAVE_FAILURE;

        return CommonResponseDTO.builder()
                .status(responseStatus.isStatus())
                .message(responseStatus.getMessage())
                .build();
    }
}
