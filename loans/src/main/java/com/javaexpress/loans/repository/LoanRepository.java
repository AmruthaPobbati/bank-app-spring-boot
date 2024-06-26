package com.javaexpress.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.loans.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

	Optional<Loan> findByMobileNumber(String mobileNumber);

    Optional<Loan> findByLoanNumber(String loanNumber);
}
