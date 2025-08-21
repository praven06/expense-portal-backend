package com.praveen.expportal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, nullable = false)
    private String categoryName;
    @Column(length = 10, nullable = false, unique = true)
    private String categoryCode;
    private BigDecimal policyLimit;


    @JsonIgnore
    @OneToMany
    private List<Expenses> expenses;
}
