package com.javaexpress.loans.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.loans.dto.LoanDTO;
import com.javaexpress.loans.service.ILoanService;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping(path = "loans")
@Slf4j
public class LoanController {

    @Autowired
    private ILoanService loanService;
    

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String createLoan(@RequestParam String mobileNumber) {
    	log.info("LoansController :: createLoan: {}", mobileNumber);
        loanService.createLoan(mobileNumber);
        return "Loan created successfully";
    }
    
    @GetMapping("/fetch")
    public LoanDTO fetchLoansDetails(@RequestParam String mobileNumber) {
    	log.info("LoansController :: fetchLoansDetails: {}", mobileNumber);
    	return loanService.fetchLoan(mobileNumber);
    }
    
    @PutMapping("/update")
    public Boolean updateLoansDetails(@RequestBody LoanDTO loansDto) {
    	log.info("LoansController :: updateLoansDetails: {}", loansDto.getLoanNumber());
    	loanService.updateLoan(loansDto);
    	return true;
    }
    
    @DeleteMapping("/delete")
    public Boolean deleteLoanDetails(@RequestParam String mobileNumber) {
    	log.info("LoansController :: deleteLoanDetails: {}", mobileNumber);
    	loanService.deleteLoan(mobileNumber);
    	return true;
    }

    
    
}
