package com.javaexpress.loans.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.javaexpress.loans.dto.LoanDTO;
import com.javaexpress.loans.entity.Loan;
import com.javaexpress.loans.enums.LoanType;
import com.javaexpress.loans.exceptions.LoanExceptionType;
import com.javaexpress.loans.exceptions.LoanRunTimenException;
import com.javaexpress.loans.repository.LoanRepository;
import com.javaexpress.loans.service.ILoanService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService{

	private LoanRepository loanRepository;

	@Override
	public void createLoan(String mobileNumber) {
		Optional<Loan> loansOptional = loanRepository.findByMobileNumber(mobileNumber);
		
		if(loansOptional.isPresent()) {
			throw new LoanRunTimenException(LoanExceptionType.LOAN_EXISTS_EXCEPTION);
		}
		
		Loan loans = new Loan();
		long randomLoanNumber = 10000000L + new Random().nextInt(777777777);
		loans.setLoanNumber(String.valueOf(randomLoanNumber));
		loans.setMobileNumber(mobileNumber);
		loans.setLoanType(LoanType.PERSONAL_LOAN);
		loans.setLoanAmount(50000);
		loans.setAmountPaid(0);
		
		loanRepository.save(loans);
		
	}

	@Override
	public LoanDTO fetchLoan(String mobileNumber) {
		Optional<Loan> loansOptional = loanRepository.findByMobileNumber(mobileNumber);
		
		if(!loansOptional.isPresent()) {
			throw new LoanRunTimenException(LoanExceptionType.LOAN_NOT_EXISTS_EXCEPTION);
		}
		var dbLoans = loansOptional.get();
		LoanDTO loansDto = new LoanDTO();
		BeanUtils.copyProperties(dbLoans, loansDto);
		loansDto.setOutstandingAmount(dbLoans.getLoanAmount() - dbLoans.getAmountPaid());
		return loansDto;
	}

	@Override
	public boolean updateLoan(LoanDTO loansDto) {
		Loan dbLoans = loanRepository.findByLoanNumber(loansDto.getLoanNumber())
				.orElseThrow(() -> new LoanRunTimenException(LoanExceptionType.LOAN_NOT_EXISTS_EXCEPTION));
		
		BeanUtils.copyProperties(loansDto, dbLoans);
		loanRepository.save(dbLoans);
		
		return true;
	}

	@Override
	public boolean deleteLoan(String mobileNumber) {
		// TODO Auto-generated method stub
		return false;
	}


}
