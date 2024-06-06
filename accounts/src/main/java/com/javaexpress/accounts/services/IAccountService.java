package com.javaexpress.accounts.services;

import org.springframework.stereotype.Service;

import com.javaexpress.accounts.dto.CustomerDTO;

@Service
public interface IAccountService {
	
	void createCheckingAccount(CustomerDTO customerDTO);

	void createSavingsAccount(CustomerDTO customerDTO);
	
	CustomerDTO fetchAccount(String mobileNumber);
	
	boolean updateAccount(CustomerDTO customerDTO);
	
	void deleteAccount(String mobileNumber);


}
