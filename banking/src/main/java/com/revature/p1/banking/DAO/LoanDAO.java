package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.Loan;
import com.revature.p1.banking.Models.Role;
import com.revature.p1.banking.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


/*
   This interface extends JpaRepository to all Loan-related classes. No custom methods are defined here, as what is
   provided by JpaRepository is sufficient by itself.
 */
@Repository
public interface LoanDAO extends JpaRepository<Loan, Integer> {

    @Query(value = "SELECT u FROM Loan u WHERE u.recipientAccount IN :accounts")
    List<Loan> findLoanByAccountList(@Param("accounts") Collection<Account> accounts);
}
