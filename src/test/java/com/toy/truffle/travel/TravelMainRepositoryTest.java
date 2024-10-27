package com.toy.truffle.travel;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.toy.truffle.travel.entity.TravelMain;
import com.toy.truffle.travel.repository.TravelMainRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TravelMainRepositoryTest {

    @Autowired
    private TravelMainRepository travelMainRepository;

    @Test
    @DisplayName("여행메인 저장")
    public void testSaveTravelMain() {
        // given
        //테스트값 세팅
        TravelMain travel = TravelMain.builder()
                .travelTitle("여행1")
                .startDate("2024-10-13")
                .endDate("2024-10-15")
                .createUserId("user")
                .build();

        // when
        // 데이터 저장
        TravelMain result = travelMainRepository.save(travel);
        //List<TravelMain> result2 = travelMainRepository.findAll();

        // then
        // 데이터 저장값 검증
        assertThat(travelMainRepository).isNotNull();
        assertThat(result.getTravelTitle()).isEqualTo("여행1");
        assertThat(result.getStartDate()).isEqualTo("2024-10-13");
        assertThat(result.getEndDate()).isEqualTo("2024-10-15");

    }
}
