package com.javaexpress.loans.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LoanExceptionType {
	
	LOAN_EXISTS_EXCEPTION(HttpStatus.BAD_REQUEST, "Loan already exists for this mobile number !"),
	LOAN_NOT_EXISTS_EXCEPTION(HttpStatus.BAD_REQUEST, "Loan doesnt for this mobile number !");
	
	
	private final HttpStatus status;
	private final String message;

}
