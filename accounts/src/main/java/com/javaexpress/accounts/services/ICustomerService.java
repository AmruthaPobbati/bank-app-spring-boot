package com.javaexpress.accounts.services;

import org.springframework.stereotype.Service;

import com.javaexpress.accounts.dto.CustomerDTO;

@Service
public interface ICustomerService {
	
	void createCustomer(CustomerDTO customerDTO);
	
	CustomerDTO fetchCustomer(String mobileNumber);
	
	boolean updateCustomer(CustomerDTO customerDTO);
	
	void deleteCustomer(String mobileNumber);

}
