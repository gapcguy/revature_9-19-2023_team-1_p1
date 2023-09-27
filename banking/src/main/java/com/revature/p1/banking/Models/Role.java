package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="Roles")
@Component
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="role_name", nullable = false)
    private String roleName;


}
