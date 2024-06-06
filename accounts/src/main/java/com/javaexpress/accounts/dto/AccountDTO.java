package com.javaexpress.accounts.dto;

import com.javaexpress.accounts.enums.AccountType;

import lombok.Data;

@Data
public class AccountDTO {
	
	private Long accountNumber;
	private AccountType accountType;
	private String branch;

}
