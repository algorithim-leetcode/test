package com.toy.truffle.travel;

import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import com.toy.truffle.travel.dto.TravelDto;
import com.toy.truffle.travel.entity.TravelMain;
import com.toy.truffle.travel.repository.TravelMainRepository;
import com.toy.truffle.travel.service.TravelService;
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
public class TravelServiceTest {
    @InjectMocks
    private TravelService travelService;

    @Mock
    private TravelMainRepository travelMainRepository;

    @Test
    @DisplayName("여행정보 저장")
    public void testSaveTravel() {
        // given
        long travelSeq = 99;
        String travelTitle = "여행타이틀";
        String startDate = "2024-10-10";
        String endDate = "2024-10-15";
        String createUserId = "tester";

        //테스트 DTO 생성
        TravelDto travelDto = TravelDto.builder()
                .travelTitle(travelTitle)
                .startDate(startDate)
                .endDate(endDate)
                .createUserId(createUserId)
                .build();

        // when
        // TravelService save 호출 예상동작 설정: stub
        when(travelMainRepository.save(any(TravelMain.class))).thenReturn(TravelMain.builder()
                        .travelSeq(travelSeq)
                        .travelTitle(travelTitle)
                        .startDate(startDate)
                        .endDate(endDate)
                        .createUserId(createUserId)
                        .build());

        //여행정보 저장 로직 호출
        CommonResponseDTO commonResponseDTO  = travelService.saveTravel(travelDto);

        // then
        // 데이터 저장 상태값 검증
        assertTrue(commonResponseDTO.isStatus());
        // 메시지 내용 검증
        assertEquals(ResponseStatus.TRAVEL_SAVE_SUCCESS.getMessage(), commonResponseDTO.getMessage());
    }
}
