package com.javaexpress.accounts.services;

import org.springframework.stereotype.Service;

import com.javaexpress.accounts.dto.CustomerDetailsDTO;

@Service
public interface ICustomerService {
	
	CustomerDetailsDTO fetchCustomer(String mobileNumber);

}
