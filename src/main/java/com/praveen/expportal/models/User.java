package com.praveen.expportal.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String username;
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(unique = true)
    private String employeeId;
    private String departmentId;
    private String managerId;
    private LocalDate createdDate;
    private LocalDate lastLogin;
    private boolean isActive;
    
    public enum Role {
        EMPLOYEE,
        MANAGER,
        ADMIN
    }
}
