package com.toy.truffle.destination;

import com.toy.truffle.destination.dto.DestinationDto;
import com.toy.truffle.destination.entity.Destination;
import com.toy.truffle.destination.repository.DestinationRepository;
import com.toy.truffle.destination.service.DestinationService;
import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class DestinationServiceTest {
    @InjectMocks
    private DestinationService destinationService;

    @Mock
    private DestinationRepository destinationRepository;

    @Test
    @DisplayName("여행지역 저장")
    public void testSaveDestination() {
        // given
        String destinationCd = "11230";
        String destinationName = "은평구";

        //테스트 DTO 생성
        DestinationDto destinationDto = DestinationDto.builder()
                .destinationCd(destinationCd)
                .destinationName(destinationName)
                .build();

        // when
        // DestinationService save 호출 예상동작 설정: stub
        when(destinationRepository.save(any(Destination.class))).thenReturn(Destination.builder()
                .destinationCd(destinationCd)
                .destinationName(destinationName)
                .build());

        //여행정보 저장 로직 호출
        CommonResponseDTO commonResponseDTO  = destinationService.saveDestination(destinationDto);

        // then
        // 데이터 저장 상태값 검증
        assertTrue(commonResponseDTO.isStatus());
        // 메시지 내용 검증
        assertEquals(ResponseStatus.DESTINATION_SAVE_SUCCESS.getMessage(), commonResponseDTO.getMessage());
    }

    @Test
    @DisplayName("여행지역 조회")
    public void testFindDestinations() {
        //TODO :: 작성필요
    }
}
