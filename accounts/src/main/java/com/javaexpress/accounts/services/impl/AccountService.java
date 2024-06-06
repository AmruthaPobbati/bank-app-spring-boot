package com.javaexpress.accounts.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.javaexpress.accounts.dto.AccountDTO;
import com.javaexpress.accounts.dto.CustomerDTO;
import com.javaexpress.accounts.entity.Account;
import com.javaexpress.accounts.enums.AccountType;
import com.javaexpress.accounts.entity.Customer;
import com.javaexpress.accounts.exceptions.AccountRunTimeException;
import com.javaexpress.accounts.exceptions.AccountsException;
import com.javaexpress.accounts.exceptions.CustomerAlreadyExistsException;
import com.javaexpress.accounts.exceptions.ResourceNotFoundException;
import com.javaexpress.accounts.repository.AccountRepository;
import com.javaexpress.accounts.repository.CustomerRepository;
import com.javaexpress.accounts.services.IAccountService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService implements IAccountService{
	
	private AccountRepository accountRepository;
	private CustomerRepository customerRepository;

	@Override
	public void createCheckingAccount(CustomerDTO customerDTO) {
		// Find existing customer by mobile number else Create Customer and save to DB
		Optional<Customer> optional = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
		if(optional.isPresent()) {
			
			throw new CustomerAlreadyExistsException(AccountsException.CUSTOMER_ALREADY_EXISTS);
		}
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerDTO, customer);
		log.info("Customer Created: " + customer);
		var dbCustomer = customerRepository.save(customer);
		
		// Create new Checking Account
		accountRepository.save(createAccount(dbCustomer, AccountType.CHECKING));

	}
	
	@Override
	public void createSavingsAccount(CustomerDTO customerDTO) {
		// Find existing customer by mobile number else Create Customer and save to DB
		Optional<Customer> optional = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
		Customer customer;
		if(optional.isPresent()) {
			customer = optional.get();
		} else {
			customer = new Customer();
		}
		BeanUtils.copyProperties(customerDTO, customer);
		
		var dbCustomer = customerRepository.save(customer);
		
		// Create new Savings Account
		accountRepository.save(createAccount(dbCustomer, AccountType.SAVINGS));

	}

	private Account createAccount(Customer customer, AccountType accountType) {
		var accountNumber = 1000000L + new Random().nextInt(900000);
		
		Account account = Account.builder()
									.accountNumber(accountNumber)
									.customerId(customer.getCustomerId())
									.accountType(accountType)
									.branch("Toronto")
									.build();
		return account;
	}
	

	@Override
	public CustomerDTO fetchAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException(AccountsException.CUSTOMER_NOT_FOUND_EXCEPTION));
		
		Account account = accountRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException(AccountsException.ACCOUNT_NOT_FOUND_EXCEPTION));
		
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customer, customerDTO);
		AccountDTO accountDTO = new AccountDTO();
		BeanUtils.copyProperties(account, accountDTO);
		customerDTO.setAccountDTO(accountDTO);
		
		return customerDTO;
	}

	@Override
	@Transactional
	public boolean updateAccount(CustomerDTO customerDTO) {
	
		boolean isUpdated = false;
		AccountDTO accountDTO = customerDTO.getAccountDTO();
		
		if(accountDTO != null) {
			Account account = accountRepository.findByAccountNumber(accountDTO.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException(AccountsException.ACCOUNT_NOT_FOUND_EXCEPTION));
			
			BeanUtils.copyProperties(accountDTO, account);
			
			accountRepository.save(account);
			
			Customer customer = customerRepository.findById(account.getCustomerId())
					.orElseThrow(() -> new ResourceNotFoundException(AccountsException.CUSTOMER_NOT_FOUND_EXCEPTION));
			
			BeanUtils.copyProperties(customerDTO, customer);
			
			customerRepository.save(customer);
			
			isUpdated = true;
		} else {
			throw new AccountRunTimeException(AccountsException.BAD_REQUEST_EXCEPTION);
		}
		return isUpdated;
	}

	@Override
	public void deleteAccount(String mobileNumber) {
		// TODO Auto-generated method stub
		
	}

}
