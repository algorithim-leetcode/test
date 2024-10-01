package com.toy.truffle.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.toy.truffle.global.dto.CommonResponseDTO;
import com.toy.truffle.global.util.Argon2PasswordEncryptor;
import com.toy.truffle.user.dto.SignUpDTO;
import com.toy.truffle.user.entity.User;
import com.toy.truffle.user.repository.UserRepository;
import com.toy.truffle.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private Argon2PasswordEncryptor passwordEncryptor;

	@InjectMocks
	private UserService userService;

	@Test
	@DisplayName("email 중복 체크 실패")
	public void testDuplicateUserEmail() {

	}

	@Test
	@DisplayName("회원가입 실패")
	public void testFailedUserSave() {

	}

	@Test
	@DisplayName("회원가입 성공")
	public void testSuccessUserSave() {
		// given
		String email = "testuser@test.com";
		String userName = "테스트사용자";
		String rawPassword = "password123";
		String encryptedPassword = "argon2EncryptedPassword";

		// RegisterUserDTO 생성
		SignUpDTO signUpDTO = SignUpDTO.builder()
			.email(email)
			.userName(userName)
			.password(rawPassword)
			.build();

		// when
		// 비밀번호 암호화 처리 모킹
		when(passwordEncryptor.encrypt(rawPassword)).thenReturn(encryptedPassword);

		// UserRepository save 처리 모킹 (DB에 저장된 것처럼 설정)
		when(userRepository.save(any(User.class))).thenReturn(User.builder()
			.id(1L)
			.email(email)
			.userName(userName)
			.password(encryptedPassword)
			.build());

		// 회원가입 로직 호출
		CommonResponseDTO commonResponseDTO = userService.signUpUser(signUpDTO);

		// then
		// 데이터 저장 상태값 검증
		assertEquals(true, commonResponseDTO.isStatus());
		// 메시지 내용 검증
		assertEquals("회원가입 성공", commonResponseDTO.getMessage());
		// 비밀번호 암호화가 1번만 호출되었는지 검증
		verify(passwordEncryptor, times(1)).encrypt(rawPassword);
		// userRepository의 save가 1번 호출되었는지 검증
		verify(userRepository, times(1)).save(any(User.class));
	}

	@Test
	@DisplayName("유저 정보 1건 조회(로그인)")
	public void testFindOneByUser() {

	}

}
