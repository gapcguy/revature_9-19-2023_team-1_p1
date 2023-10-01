package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity                  // Indicates that this class is a JPA entity, representing a database table.
@Table(name="user")      // Specifies the name of the database table for this entity.
@Component               // Marks this class as a Spring component, allowing it to be managed by Spring's IoC container.
public class User {

    /* Variable annotation usages:
        @Id             - Indicates that the following field is the primary key for this entity.
        @GeneratedValue - Specifies how the primary key values are generated (in this case, auto-increment)
        @Column         - Specifies that a variable corresponds to a database column.
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false,unique = false)
    private String password;


    @Column(nullable = false, unique = false)
    private String firstName;

    @Column(nullable = false, unique = false)
    private String lastName;

    @Column(nullable = true)
    private char role = 'U';
    public static final char MANAGER = 'M';
    public static final char EMPLOYEE = 'E';
    public static final char USER = 'U';

    /**
     * Default constructor.
     */
    public User() { }

    /**
     *
     * @param userId
     * @param username
     * @param password
     * @param firstName
     * @param lastName
     * @param role
     */
    public User(int userId,
                String username,
                String password,
                String firstName,
                String lastName,
                char role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    // Getter/setter methods.

    /**
     * The Getter method to retrieve the User Id.
     * @return the User Id.
     */
    public int getUserId() { return userId; }

    /**
     * The Getter method to retrieve the username.
     * @return the username.
     */
    public String getUsername() { return username; }

    /**
     * The Getter method to retrieve the password.
     * @return the password.
     */
    public String getPassword() { return password; }
    /**
     * The Getter method to retrieve the user's first name.
     * @return the user's first name.
     */
    public String getFirstName() { return firstName; }

    /**
     * The Getter method to retrieve the user's last name.
     * @return the user's last name.
     */
    public String getLastName() { return lastName; }

    /**
     * The Getter method to retrieve the user's role.
     * @return the user's role.
     */
    public char getRole() { return role; }

    /**
     * The Setter method to set the user id.
     * @param userId is the user id.
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * The Setter method to set the username.
     * @param username is the username.
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * The Setter method to set the password.
     * @param password is the password.
     */
    public void setPassword(String password) { this.password = password; }


    /**
     * The Setter method to set the user's first name.
     * @param firstName is the user's first name.
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }


    /**
     * The Setter method to set the user's last name.
     * @param lastName is the user's last name.
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * The Setter method to set the user's role.
     * @param role is the user's role.
     */
    public void setRole(char role) { this.role = role; }

    /**
     * Override the toString() builtin to provide a human-readable representation of the Transaction Object.
     * @return a human-readable representation of the Transaction object.
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
}
