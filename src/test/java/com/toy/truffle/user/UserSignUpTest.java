package com.toy.truffle.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.toy.truffle.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserSignUpTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("회원정보 저장")
	public void testSaveUserSignUp() {
		// given

		// when

		// then
		assertThat(userRepository).isNotNull();
	}

	@Test
	@DisplayName("비밀번호 암호화")
	public void testEncodePassword() {
		// given

		// when

		// then
	}

	@Test
	@DisplayName("입력 값 정규식 체크")
	public void testCheckValidation() {
		// given

		// when

		// then
	}
}
