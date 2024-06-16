package com.javaexpress.accounts.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDetailsDTO {
	
	private String name;
	private String mobileNumber;
	private String email;
	private List<AccountDTO> accountDTO;
	private List<CardDTO> cardDTO;
	private LoanDTO loanDTO;

}
