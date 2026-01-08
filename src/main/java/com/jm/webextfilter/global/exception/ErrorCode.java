package com.jm.webextfilter.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	// 400
	INVALID_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
	DUPLICATE_EXTENSION(HttpStatus.BAD_REQUEST, "중복된 확장자 명 입니다."),
	VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "요청 값이 올바르지 않습니다."),
	NO_MORE_CUSTOM_EXTENSION(HttpStatus.BAD_REQUEST, "커스텀 확장자의 최대 등록 개수를 초과할 수 없습니다."),

	// 404
	EXTENSION_NOT_FOUND(HttpStatus.NOT_FOUND, "확장자를 찾을 수 없습니다."),

	// 500
	INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다.");

	private final HttpStatus status;
	private final String message;

	ErrorCode(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus status() {
		return status;
	}

	public String message() {
		return message;
	}
}

