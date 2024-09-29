package com.toy.truffle.user.service;

import com.toy.truffle.global.codeEnum.ResponseStatus;
import com.toy.truffle.global.dto.CommonResponseDTO;
import com.toy.truffle.global.util.Argon2PasswordEncryptor;
import com.toy.truffle.user.dto.RegisterUserDTO;
import com.toy.truffle.user.entity.User;
import com.toy.truffle.user.repository.UserRepository;
import groovy.util.logging.Slf4j;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final Argon2PasswordEncryptor passwordEncryptor;

	@Transactional
	public CommonResponseDTO registerUser(RegisterUserDTO registerUserDTO) {
		// 비밀번호 암호화 처리
		String encryptedPassword = passwordEncryptor.encrypt(registerUserDTO.getPassword());

		User user = registerUserDTO.toBuilder()
			.password(encryptedPassword) // 암호화된 비밀번호 설정
			.build()
			.toEntity();

		Long savedUserId = userRepository.save(user).getId();

		ResponseStatus responseStatus = (savedUserId != null)
			? ResponseStatus.USER_REGISTER_SUCCESS
			: ResponseStatus.USER_REGISTER_FAILURE;

		return CommonResponseDTO.builder()
			.status(responseStatus.isStatus())
			.message(responseStatus.getMessage())
			.build();
	}
}
