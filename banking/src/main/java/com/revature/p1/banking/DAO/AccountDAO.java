package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Account;
import com.revature.p1.banking.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.Optional;
import java.util.List;

// This interface extends JpaRepository, which contains common database operations.
@Repository
public interface AccountDAO extends JpaRepository<Account, Integer>{

    /**
     * Find an account by its account ID.
     * @param accountId is the account ID for which to search.
     * @return An Optional containing the found account, or empty if the ID isn't found.
     */
    Optional<Account> findByAccountId(int accountId);

    /**
     * Find an account by its associated user.
     * @param user is the user whose account(s) are retrieved.
     * @return A list of accounts associated with the given user.
     */
    List<Account> findByUser(User user);


}
