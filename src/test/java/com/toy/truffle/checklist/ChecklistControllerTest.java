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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
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
        String saveTravelUrl = "/checklist/saveChecklist";

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
                .andExpect(jsonPath("$.message").value(ResponseStatus.SAVE_SUCCESS.getMessage())) //message 값 비교
                .andExpect(jsonPath("$.status").value(true)) //status 값 비교
                .andReturn(); // MvcResult 객체로 리턴
    }


    @Test
    @DisplayName("[Controller] 체크리스트 업데이트")
    public void testUpdateChecklist() throws Exception {
        // given
        String updateChecklistUrl = "/checklist/updateChecklist";

        // 테스트 DTO 생성
        ChecklistDto checklistDto = ChecklistDto.builder()
                .checklistSeq(1) // 업데이트할 체크리스트 ID
                .travelSeq(1)
                .parentChecklistSeq(null)
                .checklistName("필수준비물 - 수정")
                .description("대메뉴1 - 수정")
                .build();

        // when
        // checklistService updateChecklist 함수 호출 예상 동작 설정: stub
        when(checklistService.updateChecklist(any(ChecklistDto.class)))
                .thenReturn(CommonResponseDTO.builder()
                        .status(true)
                        .message(ResponseStatus.SAVE_SUCCESS.getMessage())
                        .build());

        // HTTP 요청
        ResultActions resultActions = mockMvc.perform(put(updateChecklistUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(checklistDto)));

        // then
        // 응답값 확인
        MvcResult mvcResult = resultActions
                .andExpect(status().isOk()) // 200
                .andExpect(jsonPath("$.message").value(ResponseStatus.SAVE_SUCCESS.getMessage())) // message 값 비교
                .andExpect(jsonPath("$.status").value(true)) // status 값 비교
                .andReturn(); // MvcResult 객체로 리턴
    }

    @Test
    @DisplayName("[Controller] 체크리스트 삭제")
    public void testDeleteChecklist() throws Exception {
        // given
        Long checklistId = 1L;
        String deleteChecklistUrl = "/checklist/deleteChecklist/" + checklistId;

        // when
        // checklistService deleteChecklist 함수 호출 예상 동작 설정: stub
        when(checklistService.deleteChecklist(eq(checklistId)))
                .thenReturn(CommonResponseDTO.builder()
                        .status(true)
                        .message(ResponseStatus.SAVE_SUCCESS.getMessage())
                        .build());

        // HTTP 요청
        ResultActions resultActions = mockMvc.perform(delete(deleteChecklistUrl)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        // 응답값 확인
        MvcResult mvcResult = resultActions
                .andExpect(status().isOk()) // 200
                .andExpect(jsonPath("$.message").value(ResponseStatus.SAVE_SUCCESS.getMessage())) // message 값 비교
                .andExpect(jsonPath("$.status").value(true)) // status 값 비교
                .andReturn(); // MvcResult 객체로 리턴
    }
}
