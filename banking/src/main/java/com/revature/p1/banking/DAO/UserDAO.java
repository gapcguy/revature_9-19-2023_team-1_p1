package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* By extending JPARepository, we get access to various DAO Methods that we don't need to write.
    ctrl + click on JpaRepository tp see what methods are provided for us already.

    JpaRepository takes two values in its generic -
    1) The Table/model we're dealing with
    2) The data type of the model's id (in wrapper class form)
 */
@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    /* Extending the JpaRepository will give us a bunch of methods out of the box. See this custom DAO method below:

        We want to be able to find an employee by username. Unfortunately, Spring Data only knows the primary key;
        therefore, we need to define our own. Spring Data *is* smart enough to implement this method for us -- we
        merely define the abstraction.  */
    Optional<User> findByUsername(String username);

    /* How this works:
        By having a method name starting with "findBy", and ending in the variable you want to find. Spring needs
        a method name to be in camelCase, or it will throw very vague errors. Also, it cannot have underscores.  */

     /* There are a lot of options for custom DAO methods. Look into things like native queries if you really need
        specific DAO methods.  */
}
