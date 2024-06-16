package com.javaexpress.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.accounts.dto.CustomerDetailsDTO;
import com.javaexpress.accounts.services.ICustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "customer")
@Slf4j
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("fetch")
	public CustomerDetailsDTO fetchCustomerDetails(@RequestParam String mobileNumber) {
		log.info("CustomerController :: fetchCustomerDetails");
		return customerService.fetchCustomer(mobileNumber);
	}

}
