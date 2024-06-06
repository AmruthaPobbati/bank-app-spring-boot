package com.javaexpress.accounts.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.accounts.dto.CustomerDTO;
import com.javaexpress.accounts.services.IAccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@AllArgsConstructor
@Slf4j
public class AccountController {
	
	private IAccountService accountService;
	
	@PostMapping("createChecking")
	String createCheckingAccount(@RequestBody CustomerDTO customerDTO) {
		log.info("AccountController :: createAccount");
		accountService.createCheckingAccount(customerDTO);
		return "Account Created Successfully !";
	}
	
	@PostMapping("createSavings")
	String createSavingsAccount(@RequestBody CustomerDTO customerDTO) {
		log.info("AccountController :: createAccount");
		accountService.createSavingsAccount(customerDTO);
		return "Savings Account Created Successfully !";
	}
	
	@GetMapping("fetch")
	public CustomerDTO getAccount(@RequestParam String mobileNumber) {
		log.info("AccountController :: getAccount");
		return accountService.fetchAccount(mobileNumber);
	}
	
	@PutMapping("update")
	public boolean updateAccount(@RequestBody CustomerDTO customerDTO) {
		log.info("AccountController :: updateAccount");
		return accountService.updateAccount(customerDTO);
	}
	
	@DeleteMapping("delete") 
	public void deleteAccount(@RequestParam String mobileNumber) {
		log.info("AccountController :: deleteAccount");
		accountService.deleteAccount(mobileNumber);
	}

}
