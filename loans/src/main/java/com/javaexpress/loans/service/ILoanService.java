package com.javaexpress.loans.service;

import com.javaexpress.loans.dto.LoanDTO;

public interface ILoanService {

    void createLoan(String mobileNumber);
    LoanDTO fetchLoan(String mobileNumber);
    boolean updateLoan(LoanDTO loansDto);
    boolean deleteLoan(String mobileNumber);

}
