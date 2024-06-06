package com.javaexpress.accounts.dto;

import lombok.Data;

@Data
public class CustomerDTO {
	
	private String name;
	private String mobileNumber;
	private String email;
	private AccountDTO accountDTO;

}
