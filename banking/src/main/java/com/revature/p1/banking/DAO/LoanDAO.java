package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
   This interface extends JpaRepository to all Loan-related classes. No custom methods are defined here, as what is
   provided by JpaRepository is sufficient by itself.
 */
@Repository
public interface LoanDAO extends JpaRepository<Loan, Integer> { }
