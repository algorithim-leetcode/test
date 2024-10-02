package com.toy.truffle.user.dto;

import com.toy.truffle.user.entity.User;
import java.io.Serial;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class SignUpDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = -2459221140206834804L;

	private String email;
	private String userName;
	private String password;

	public User toEntity() {
		return User.builder()
			.email(email)
			.userName(userName)
			.password(password)
			.build();
	}
}
