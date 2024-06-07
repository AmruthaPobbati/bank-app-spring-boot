package com.javaexpress.cards.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardExceptionType {
	
	CARD_EXISTS_EXCEPTION(HttpStatus.BAD_REQUEST, "Card Already exists for this mobile number !"),
	CARD_NOT_EXISTS_EXCEPTION(HttpStatus.BAD_REQUEST, "Card doesnt for this mobile number !");
	

	private final HttpStatus status;
	private final String message;
}
