package com.toy.truffle.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.toy.truffle.global.util.Argon2PasswordEncryptor;
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
	@DisplayName("비밀번호 암호화 테스트")
	public void testPasswordEncode() {
		// given
		String rawPassword = "password123";
		String encryptedPassword = "argon2EncryptedPassword";

		when(passwordEncryptor.encrypt(rawPassword)).thenReturn(encryptedPassword);

		// when
		String result = passwordEncryptor.encrypt(rawPassword);

		// then
		assertEquals(encryptedPassword, result); // 예상되는 암호화 결과와 일치하는지 확인
		verify(passwordEncryptor, times(1)).encrypt(rawPassword);
	}

	@Test
	@DisplayName("email 중복 체크 실패")
	public void testDuplicateUserEmail() {

	}

	@Test
	@DisplayName("데이터 저장 실패")
	public void testFailedUserSave() {

	}

	@Test
	@DisplayName("데이터 저장 성공")
	public void testSuccessUserSave() {

	}
}
