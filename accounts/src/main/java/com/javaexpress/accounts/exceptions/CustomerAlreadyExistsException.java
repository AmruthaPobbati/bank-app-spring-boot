package com.javaexpress.accounts.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerAlreadyExistsException extends RuntimeException{
	
	private final AccountsException accountException;
}
