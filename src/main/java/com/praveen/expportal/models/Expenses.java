package com.praveen.expportal.models;

import java.time.LocalDate;
import java.util.Locale.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private int amount;
    private String description;
    private LocalDate expenseDate;
    @ManyToOne
    private ExpenseCategory category;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String remarks;
    private LocalDate submissionDate;
    private LocalDate approvalDate;
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Departments department;
    @OneToOne
    private Approval approval;

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
}

