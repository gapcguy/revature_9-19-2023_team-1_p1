package com.revature.p1.banking.Models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

// Since user roles have been folded into the user model, this is unneeded by the current iteration of the program.
@Entity                 // Indicates that this class is a JPA entity, representing a database table.
@Table(name="role")     // Specifies the name of the database table for this entity.
@Component              // Marks this class as a Spring component, allowing it to be managed by Spring's IoC container.
public class Role {
    /* Variable annotation usages:
        @Id             - Indicates that the following field is the primary key for this entity.
        @GeneratedValue - Specifies how the primary key values are generated (in this case, auto-increment)
        @Column         - Specifies that a variable corresponds to a database column.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @Column(nullable = false, unique = true)
    private String roleName;

    @Column(nullable = false)
    private Double roleSalary;

    /**
     * Getter method for retrieving the role ID.
     * @return the role ID.
     */
    public int getRoleId() { return roleId; }

    /**
     * Getter method for retrieving the role name.
     * @return the role name.
     */
    public String getRoleName() { return roleName; }

    /**
     * Getter method for getting the role salary
     * @param roleSalary the role salary
     * @return the role salary.
     */
    public double getRoleSalary(double roleSalary) { return roleSalary; }

    /**
     * Setter method for setting the role ID.
     * @param roleId is the role ID.
     */
    public void setRoleId(int roleId) { this.roleId = roleId; }

    /**
     * Setter method for setting the Role name.
     * @param roleName is the role name.
     */
    public void setRoleName(String roleName) { this.roleName = roleName; }

    /**
     * Setter method for setting the Role salary.
     * @param roleSalary is the Role salary.
     */
    public void setRoleSalary(double roleSalary) { this.roleSalary = roleSalary; }

    /**
     * Override the toString() builtin to provide a human-readable representation of the Role Object.
     * @return a human-readable representation of the Role object.
     */
    @Override
    public String toString() {
        return "Role{ "          +
                "roleId="        + roleId     +
                ", roleName='"   + roleName   + '\'' +
                ", roleSalary='" + roleSalary + '\'' +
                '}';
    }
}
