package com.javaexpress.cards.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CardsRunTimeException.class)
	public ResponseEntity<ErrorAPI> handleException(final CardsRunTimeException exception) {
		return makeErrorResponseEntity(exception.getCardException());
	}
	
	private ResponseEntity<ErrorAPI> makeErrorResponseEntity(final CardExceptionType carException) {
		ErrorAPI error = ErrorAPI.builder()
								.statusCode(carException.getStatus().value())
								.title(carException.getMessage())
								.status(carException.getStatus().getReasonPhrase())
								.details(carException.getMessage())
								.currentTime(LocalDateTime.now())
								.build();
		
		return ResponseEntity.status(carException.getStatus()).body(error);
	}

}

