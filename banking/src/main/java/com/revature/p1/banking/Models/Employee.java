package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Entity // This annotation registers this class as a DB Table
@Table(name="employees")
@Component //This is one of the 4 stereotype annotations
//A stereotype annotation makes a class a bean (so it can inject dependencies and be injected as a dep.)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    //TODO: add Role when we talk about Spring Data

    //boilerplate--------------------------------

    public Employee() {}

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Employee(int employeeId, String username, String password) {
        this.employeeId = employeeId;
        this.username = username;
        this.password = password;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}