package com.toy.truffle.destination;

import com.toy.truffle.destination.entity.Destination;
import com.toy.truffle.destination.repository.DestinationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class DestinationRepositoryTest {

    @Autowired
    private DestinationRepository destinationRepository;

    @Test
    @DisplayName("여행지역 테이블 저장")
    public void testSaveDestination() {
        // given
        //테스트값 세팅
        Destination destination = Destination.builder()
                .destinationCd("11230")
                .destinationName("은평구")
                .build();

        // when
        // 데이터 저장
        Destination result = destinationRepository.save(destination);

        // then
        // 데이터 저장값 검증
        assertThat(destinationRepository).isNotNull();
        assertThat(result.getDestinationCd()).isEqualTo("11230");
        assertThat(result.getDestinationName()).isEqualTo("은평구");

    }

}
