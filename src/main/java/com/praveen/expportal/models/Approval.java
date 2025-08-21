package com.praveen.expportal.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.praveen.expportal.models.Expenses.Status;

import jakarta.persistence.Entity;
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
public class Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long expenseId;
    private String approverId;
    private Status approvalStatus;
    private LocalDate approvalDate;
    private String comments;
    
    
}
