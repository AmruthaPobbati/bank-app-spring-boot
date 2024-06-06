package com.javaexpress.accounts.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorAPI> handleException(final ResourceNotFoundException exception) {
		return makeErrorResponseEntity(exception.getAccountException());
	}
	
	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorAPI> handleException(final CustomerAlreadyExistsException exception) {
		return makeErrorResponseEntity(exception.getAccountException());
	}
	
	private ResponseEntity<ErrorAPI> makeErrorResponseEntity(final AccountsException accountException) {
		ErrorAPI error = ErrorAPI.builder()
								.statusCode(accountException.getStatus().value())
								.title(accountException.getMessage())
								.status(accountException.getStatus().getReasonPhrase())
								.details(accountException.getMessage())
								.currentTime(LocalDateTime.now())
								.build();
		
		return ResponseEntity.status(accountException.getStatus()).body(error);
	}

}
