package com.javaexpress.accounts.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardType {
	
	CC_CASHBACK(10000.0),
	CC_PLATINUM(100000.0);
	
	private final double limit;

}


