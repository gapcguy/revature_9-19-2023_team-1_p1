package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
   This interface extends JpaRepository to all Role-related classes. No custom methods are defined here, as what is
   provided by JpaRepository is sufficient by itself. Since roles are folded into the user Model, this is unneeded by
   the current iteration of the project.
 */
@Repository
public interface RoleDAO extends JpaRepository<Role, Integer> { }
