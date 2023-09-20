package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/* By extending JPARepository, we get access to various DAO Methods that we don't need to write.
    ctrl + click on JpaRepository tp see what methods are provided for us already.

    JpaRepository takes two values in its generic -
    1) The Table/model we're dealing with
    2) The data type of the model's id (in wrapper class form)
 */
@Repository // We want this interface to be a bean.
public interface UserDAO extends JpaRepository<User, Integer> { }
