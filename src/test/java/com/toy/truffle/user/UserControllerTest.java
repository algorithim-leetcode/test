package com.toy.truffle.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.toy.truffle.global.dto.CommonResponseDTO;
import com.toy.truffle.user.controller.UserController;
import com.toy.truffle.user.dto.SignUpDTO;
import com.toy.truffle.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	@DisplayName("[Controller] 사용자 등록 성공 테스트")
	public void testSignUp() throws Exception {
		// given
		final String signUpUrl = "/api/v1/signUp";
		final String redirectUrl  = "/api/v1/login";
		// 회원가입 DTO 생성
		final SignUpDTO signUpDTO = createSignInDTO();

		when(userService.signUpUser(any(SignUpDTO.class)))
			.thenReturn(CommonResponseDTO.builder().status(true).message("Success").build());

		// when
		ResultActions resultActions = mockMvc.perform(post(signUpUrl)
			.param("email", signUpDTO.getEmail())
			.param("userName", signUpDTO.getUserName())
			.param("password", signUpDTO.getPassword())
			.contentType(MediaType.APPLICATION_FORM_URLENCODED));

		// then
		assertThat(userController).isNotNull();
		assertThat(mockMvc).isNotNull();
		// 회원가입 완료 후 로그인 페이지로 리디랙션
		resultActions
			.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl(redirectUrl));
	}

	// 회원가입 DTO 생성
	private SignUpDTO createSignInDTO() {
		return SignUpDTO.builder()
			.email("test_user@test.com")
			.password("test_password")
			.userName("홍길동")
			.build();
	}
}