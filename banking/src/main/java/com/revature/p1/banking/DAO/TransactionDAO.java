package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT u.transactionAmount FROM Transaction u WHERE u.toAccount IN :accounts")
    List<BigDecimal> findTransactionByAccountList(@Param("accounts") Collection<Account> accounts);


}
