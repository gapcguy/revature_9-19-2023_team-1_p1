package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    /**
     * This interface extends JpaRepository, which provides common database operations for the User entity; and
     * exists to find a user by their username. It returns an Optional to handle cases where the user may not exist.
     *
     * @param username  is the Username to search for.
     * @return          an Optional containing the found user, or empty if the user is not found.
     */
    Optional<User> findByUsername(String username);
}
