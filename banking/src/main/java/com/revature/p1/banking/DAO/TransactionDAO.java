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

// This interface extends JpaRepository to all Role-related classes.
@Repository
public interface TransactionDAO extends JpaRepository<Transaction, Integer> {

    /**
     * This custom query method uses JPQL to retrieve transactions based on a list of target accounts. The query
     * selects transactions where the 'toAccount' is in the provided collection of accounts.
     *
     * @param accounts is a collection of target accounts used to search for transactions.
     * @return A list of transactions where the target account matches one in the provided collection.
     */
    @Query(value = "SELECT u FROM Transaction u WHERE u.toAccount IN :accounts")
    List<Transaction> findTransactionByAccountList(@Param("accounts") Collection<Account> accounts);


}
