package com.javaexpress.accounts.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.javaexpress.accounts.dto.AccountDTO;
import com.javaexpress.accounts.dto.CardDTO;
import com.javaexpress.accounts.dto.CustomerDetailsDTO;
import com.javaexpress.accounts.dto.LoanDTO;
import com.javaexpress.accounts.entity.Account;
import com.javaexpress.accounts.entity.Customer;
import com.javaexpress.accounts.exceptions.AccountExceptionType;
import com.javaexpress.accounts.exceptions.AccountRunTimeException;
import com.javaexpress.accounts.repository.AccountRepository;
import com.javaexpress.accounts.repository.CustomerRepository;
import com.javaexpress.accounts.services.ICustomerService;
import com.javaexpress.accounts.services.clients.CardFeignClient;
import com.javaexpress.accounts.services.clients.LoanFeignClient;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService implements ICustomerService{
	
	private CustomerRepository customerRepository;
	
	private AccountRepository accountRepository;
	
	private CardFeignClient cardFeignClient;
	
	private LoanFeignClient loanFeignClient;
	
	

	@Override
	public CustomerDetailsDTO fetchCustomer(String mobileNumber) {
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
								.orElseThrow(() -> new AccountRunTimeException(AccountExceptionType.CUSTOMER_NOT_FOUND_EXCEPTION));
		
		
		List<Account> accounts = accountRepository.findByCustomerId(customer.getCustomerId())
								.orElseThrow(() -> new AccountRunTimeException(AccountExceptionType.ACCOUNT_NOT_FOUND_EXCEPTION));
		
		ModelMapper modelMapper = new ModelMapper();
		List<AccountDTO> accountDTOs = modelMapper.map(accounts, new TypeToken<List<AccountDTO>>(){}.getType());
		
		List<CardDTO> cardDTOs = cardFeignClient.getAllCards(customer.getMobileNumber());
		
		LoanDTO loanDTO = loanFeignClient.fetchLoansDetails(customer.getMobileNumber());
		
		return CustomerDetailsDTO.builder()
				.name(customer.getName())
				.mobileNumber(customer.getMobileNumber())
				.email(customer.getEmail())
				.accountDTO(accountDTOs)
				.cardDTO(cardDTOs)
				.loanDTO(loanDTO).build();
	}

}
