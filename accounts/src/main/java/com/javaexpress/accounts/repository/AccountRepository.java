package com.javaexpress.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.accounts.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByCustomerId(Long customerId);
	
	Optional<Account> findByAccountNumber(Long accountNumber);

}
