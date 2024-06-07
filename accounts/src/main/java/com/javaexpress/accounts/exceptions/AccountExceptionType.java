package com.javaexpress.accounts.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountExceptionType {
	
	CUSTOMER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "Mobile Number Already Exists !"),
	CUSTOMER_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "Customer Not Found !"),
	ACCOUNT_NOT_FOUND_EXCEPTION(HttpStatus.BAD_REQUEST, "Account Not Found !"),
	BAD_REQUEST_EXCEPTION(HttpStatus.BAD_REQUEST, "Bad Request !");
	
	private final HttpStatus status;
	private final String message;

}
