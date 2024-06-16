package com.javaexpress.accounts.services.clients;

import org.springframework.stereotype.Component;

import com.javaexpress.accounts.dto.LoanDTO;

@Component
public class LoanFallBack implements LoanFeignClient {

	@Override
	public LoanDTO fetchLoansDetails(String mobileNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
