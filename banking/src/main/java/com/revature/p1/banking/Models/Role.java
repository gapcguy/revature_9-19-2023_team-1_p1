package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name="roles")
@Component
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    @Column(nullable = false)
    private Double roleSalary;

    public int    getRoleId    ()                  { return roleId;                }
    public String getRoleName  ()                  { return roleName;              }
    public double getRoleSalary(double roleSalary) { return roleSalary;            }
    public void   setRoleId    (int roleId)        { this.roleId = roleId;         }
    public void   setRoleName  (String roleName)   { this.roleName = roleName;     }
    public void   setRoleSalary(double roleSalary) { this.roleSalary = roleSalary; }

    @Override
    public String toString() {
        return "Role{ "          +
                "roleId="        + roleId     +
                ", roleName='"   + roleName   + '\'' +
                ", roleSalary='" + roleSalary + '\'' +
                '}';
    }
}


