package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="customers")
@Component
public class User {

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", nullable = true)
    private Role role;


    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role     = role;
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username   = username;
        this.password   = password;
        this.firstName  = firstName;
        this.lastName   = lastName;
    }

    public int getUserId      ()                 { return userId;     }
    public String getUsername ()                 { return username;   }
    public String getPassword ()                 { return password;   }
    public String getFirstName()                 { return firstName;  }
    public String getLastName ()                 { return lastName;   }
    public Role getRole       ()                 { return role;       }
    public void setUserId     (int customerId)   { this.userId = customerId;     }
    public void setUsername   (String username)  { this.username = username;     }
    public void setPassword   (String password)  { this.password = password;     }

    public void setFirstName  (String firstName) { this.firstName = firstName;   }
    public void setLastName   (String lastName)  { this.lastName = lastName;     }
    public void setRole       (Role role)        { this.role = role;             }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
