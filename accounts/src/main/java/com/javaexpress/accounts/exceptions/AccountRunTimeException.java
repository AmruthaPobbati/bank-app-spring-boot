package com.javaexpress.accounts.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountRunTimeException extends RuntimeException{
	private final AccountExceptionType accountException;
}
