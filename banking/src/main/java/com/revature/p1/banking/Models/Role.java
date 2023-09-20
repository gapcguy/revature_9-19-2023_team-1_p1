package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Roles")
@Component
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(nullable = false, unique = true)
    private String roleName;


}


