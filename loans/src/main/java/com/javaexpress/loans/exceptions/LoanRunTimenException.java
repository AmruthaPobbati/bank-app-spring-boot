package com.javaexpress.loans.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoanRunTimenException extends RuntimeException {
	
	private final LoanExceptionType loanException;

}
