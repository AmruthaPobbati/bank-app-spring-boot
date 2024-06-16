package com.javaexpress.accounts.services;

import org.springframework.stereotype.Service;

import com.javaexpress.accounts.dto.AccountDTO;
import com.javaexpress.accounts.dto.CustomerDTO;

@Service
public interface IAccountService {
	
	void createCheckingAccount(CustomerDTO customerDTO);

	void createSavingsAccount(CustomerDTO customerDTO);
	
	CustomerDTO fetchAccounts(String mobileNumber);
	
	boolean updateAccount(AccountDTO accountDTO);
	
	void deleteAccount(String mobileNumber);


}
