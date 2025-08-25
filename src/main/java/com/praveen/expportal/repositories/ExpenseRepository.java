package com.praveen.expportal.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.praveen.expportal.models.Expenses;
import com.praveen.expportal.models.Expenses.Status;

public interface ExpenseRepository extends JpaRepository<Expenses,Long>{

    List<Expenses> findByDepartmentManagerIdAndStatus(String managerId, Status status);

    List<Expenses> findByEmployeeId(String employeeId);
    
    Page<Expenses> findByEmployeeId(String employeeId, Pageable pageable);
    List<Expenses> findByDepartmentId(Long deptId);
    
}
