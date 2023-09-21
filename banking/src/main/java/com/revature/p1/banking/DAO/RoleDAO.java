package com.revature.p1.banking.DAO;

import com.revature.p1.banking.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Integer> { }
