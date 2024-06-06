package com.javaexpress.accounts.entity;

import com.javaexpress.accounts.enums.AccountType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

	@Id
	@Column(name = "account_number", nullable = false, unique = true)
	private Long accountNumber;

	@Column(name = "customer_id", nullable = false)
	private Long customerId;

	@Column(name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	
	private String branch;
}
