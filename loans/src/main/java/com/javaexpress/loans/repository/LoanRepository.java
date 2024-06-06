package com.javaexpress.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.loans.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

}
