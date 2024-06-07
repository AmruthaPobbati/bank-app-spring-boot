package com.javaexpress.cards.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CardsRunTimeException extends RuntimeException {
	
	private final CardExceptionType cardException;

}
