package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="Account")
@Component
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    @Column(nullable=false, unique = true)
    private Date date;

    @Column(nullable = false, unique = false)
    private BigDecimal balance;

    @ManyToMany
    @JoinColumn(name = "userIdFk")
    private User user;

}
