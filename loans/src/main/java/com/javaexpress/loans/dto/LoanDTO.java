package com.javaexpress.loans.dto;


import com.javaexpress.loans.enums.LoanType;

import lombok.Data;


@Data
public class LoanDTO {
    private String mobileNumber;
    private String loanNumber;
    private LoanType loanType;
    private double loanAmount;
    private double amountPaid;
    private double outstandingAmount;
}
