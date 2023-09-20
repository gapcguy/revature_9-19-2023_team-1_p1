package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Entity
@Table(name="customers")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false,unique = false)
    private String password;

    @Column(nullable = false, unique = true)
    private int accountNum;

    @Column(nullable = false, unique = false)
    private String firstName;

    @Column(nullable = false, unique = false)
    private String lastName;

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, int accountNum, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.accountNum = accountNum;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getCustomerId() { return customerId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public int getAccountNum() { return accountNum; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setAccountNum(int accountNum) { this.accountNum = accountNum; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", accountNum=" + accountNum +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
