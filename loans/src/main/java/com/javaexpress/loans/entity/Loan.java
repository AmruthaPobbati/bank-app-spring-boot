package com.javaexpress.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	private String loanType;
	private double loanAmount;
	private double amountPaid;
	
	public double loanBalance() {
		return loanAmount - amountPaid;
	} 

}
