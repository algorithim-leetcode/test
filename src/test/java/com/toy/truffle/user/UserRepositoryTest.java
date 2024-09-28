package com.toy.truffle.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.toy.truffle.user.entity.User;
import com.toy.truffle.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Test
	@DisplayName("회원정보 저장")
	public void testSaveUserSignUp() {
		// given
		final User saveUser = User.builder()
			.userName("테스트유저")
			.email("test@test.com")
			.password("Asdf1234!@")
			.build();

		// when
		final User result = userRepository.save(saveUser);

		// then
		assertThat(userRepository).isNotNull();
		assertThat(result.getUserName()).isEqualTo("테스트유저");
		assertThat(result.getEmail()).isEqualTo("test@test.com");
		assertThat(result.getPassword()).isEqualTo("Asdf1234!@");
	}
}
