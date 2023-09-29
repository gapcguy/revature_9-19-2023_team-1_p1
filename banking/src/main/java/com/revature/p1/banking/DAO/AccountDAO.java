package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.Optional;
import java.util.List;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{

    Optional<Account> findByAccountId(int accountId);

    List<Account> findByUser(User user);


}
