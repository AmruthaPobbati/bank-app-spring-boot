package com.javaexpress.loans.entity;

import com.javaexpress.loans.enums.LoanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	private String loanNumber;
	
	@Enumerated(EnumType.STRING)
	private LoanType loanType;
	
	private double loanAmount;
	
	private double amountPaid;
}
