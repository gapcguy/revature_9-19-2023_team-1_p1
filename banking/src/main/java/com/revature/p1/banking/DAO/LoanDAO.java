package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDAO extends JpaRepository<Loan, Long> { }
