package com.toy.truffle.destination.service;

import com.toy.truffle.destination.dto.DestinationDto;
import com.toy.truffle.destination.entity.Destination;
import com.toy.truffle.destination.repository.DestinationRepository;
import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DestinationService {
    private final DestinationRepository destinationRepository;

    /**
     * 여행지역 저장
     */
    public CommonResponseDTO saveDestination(DestinationDto destinationDto) {
        Destination destination = destinationDto.toEntity();

        Destination result = destinationRepository.save(destination);

        ResponseStatus responseStatus = (result != null)
                ? ResponseStatus.DESTINATION_SAVE_SUCCESS
                : ResponseStatus.DESTINATION_SAVE_FAILURE;

        return CommonResponseDTO.builder()
                .status(responseStatus.isStatus())
                .message(responseStatus.getMessage())
                .build();
    }
}
