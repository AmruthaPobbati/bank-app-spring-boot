package com.javaexpress.accounts.dto;

import java.util.List;

import lombok.Data;

@Data
public class CustomerDTO {
	
	private String name;
	private String mobileNumber;
	private String email;
	private List<AccountDTO> accountDTOs;

}
