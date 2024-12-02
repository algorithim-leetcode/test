package com.toy.truffle.travel;

import com.toy.truffle.destination.entity.Destination;
import com.toy.truffle.destination.repository.DestinationRepository;
import com.toy.truffle.travel.entity.TravelMain;
import com.toy.truffle.travel.entity.TrvlDstnMapping;
import com.toy.truffle.travel.entity.TrvlDstnMappingId;
import com.toy.truffle.travel.repository.TravelMainRepository;
import com.toy.truffle.travel.repository.TrvlDstnMappingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrvlDstnMappingRepositoryTest {

    @Autowired
    private TravelMainRepository travelMainRepository;

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private TrvlDstnMappingRepository trvlDstnMappingRepository;

    @Test
    @DisplayName("여행지, 여행, 매핑테이블 저장")
    public void testSaveTravelMain() {
        // given
        //테스트값 세팅
        TravelMain travelMain = TravelMain.builder()
                .travelTitle("여행1")
                .startDate(LocalDate.of(2024, 10, 13))
                .endDate(LocalDate.of(2024, 10, 15))
                .createUserId("user")
                .build();

        Destination destination = Destination.builder()
                .destinationCd("11230")
                .destinationName("은평구")
                .build();

        //TravelMain 와 Destination 먼저 저장
        TravelMain result1 = travelMainRepository.save(travelMain); 
        Destination result2 = destinationRepository.save(destination);

        //저장후 리턴 받은 키값을 세팅
        TrvlDstnMappingId trvlDstnMappingId = new TrvlDstnMappingId();
        trvlDstnMappingId.setTravelSeq(result1.getTravelSeq());
        trvlDstnMappingId.setDestinationCd(result2.getDestinationCd());

        TrvlDstnMapping trvlDstnMapping = TrvlDstnMapping.builder()
                .id(trvlDstnMappingId)
                .travelMain(result1)
                .destination(result2)
                .build();

        // when
        // 매핑테이블 데이터 저장
        TrvlDstnMapping result3 = trvlDstnMappingRepository.save(trvlDstnMapping);

        // then
        // 데이터 저장값 검증
        assertThat(trvlDstnMappingRepository).isNotNull();

    }
}
