package com.javaexpress.loans.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(LoanRunTimenException.class)
	public ResponseEntity<ErrorAPI> handleLoanException(LoanRunTimenException loanException) {
		return makeErrorResponeEntity(loanException.getLoanException());
	}
	
	private ResponseEntity<ErrorAPI> makeErrorResponeEntity(LoanExceptionType loanException) {
		ErrorAPI error = ErrorAPI.builder()
				.statusCode(loanException.getStatus().value())
				.title(loanException.getMessage())
				.status(loanException.getStatus().getReasonPhrase())
				.details(loanException.getMessage())
				.currentTime(LocalDateTime.now())
				.build();

		return ResponseEntity.status(loanException.getStatus()).body(error);
		
	}

}
