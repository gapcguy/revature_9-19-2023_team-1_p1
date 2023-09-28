package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{

    Optional<Account> findByAccountId(int accountId);


}
