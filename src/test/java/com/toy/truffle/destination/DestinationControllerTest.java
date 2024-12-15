package com.toy.truffle.destination;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.truffle.destination.controller.DestinationController;
import com.toy.truffle.destination.service.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DestinationControllerTest {
    @InjectMocks
    private DestinationController destinationController;

    @Mock
    private DestinationService destinationService;

    @Mock
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(destinationController).build();
    }

    @Test
    @DisplayName("[Controller] 여행지 조회")
    public void testFindDestinations() throws Exception {
        //TODO :: 작성필요
    }
}
