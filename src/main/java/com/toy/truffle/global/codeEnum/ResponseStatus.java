package com.toy.truffle.global.codeEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

	USER_REGISTER_SUCCESS(true, "회원가입 성공"),
	USER_REGISTER_FAILURE(false, "회원가입 실패");

	private final boolean status;
	private final String message;

}
