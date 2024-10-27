package com.toy.truffle.checklist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.truffle.checklist.controller.ChecklistController;
import com.toy.truffle.checklist.dto.ChecklistDto;
import com.toy.truffle.checklist.service.ChecklistService;
import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ChecklistControllerTest {
    @InjectMocks
    private ChecklistController checklistController;

    @Mock
    private ChecklistService checklistService;

    @Mock
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(checklistController).build();
    }

    @Test
    @DisplayName("[Controller] 체크리스트 저장")
    public void testSaveChecklist() throws Exception {
        // given
        String saveTravelUrl = "/checklist/info";

        //테스트 DTO 생성
        ChecklistDto checklistDto = ChecklistDto.builder()
                .travelSeq(1)
                .parentChecklistSeq(null)
                .checklistName("필수준비물")
                .description("대메뉴1")
                .build();

        // when
        // checklistService saveChecklist 함수 호출 예상동작 설정: stub
        when(checklistService.saveChecklist(any(ChecklistDto.class)))
                .thenReturn(CommonResponseDTO.builder()
                        .status(true)
                        .message(ResponseStatus.SAVE_SUCCESS.getMessage())
                        .build());

        //http 요청
        ResultActions resultActions = mockMvc.perform(post(saveTravelUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(checklistDto)));

        //then
        //응답값 확인
        MvcResult mvcResult = resultActions
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$.message").value(ResponseStatus.TRAVEL_SAVE_SUCCESS.getMessage())) //message 값 비교
                .andExpect(jsonPath("$.status").value(true)) //status 값 비교
                .andReturn(); // MvcResult 객체로 리턴
    }
}
