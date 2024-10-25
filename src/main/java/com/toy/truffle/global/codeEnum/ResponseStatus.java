package com.toy.truffle.global.codeEnum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {

	USER_REGISTER_SUCCESS(true, "회원가입 성공"),
	USER_REGISTER_FAILURE(false, "회원가입 실패"),
	TRAVEL_SAVE_SUCCESS(true, "정상적으로 저장되었습니다."),
	TRAVEL_SAVE_FAILURE(false, "저장에 실패하였습니다.");

	private final boolean status;
	private final String message;

}
