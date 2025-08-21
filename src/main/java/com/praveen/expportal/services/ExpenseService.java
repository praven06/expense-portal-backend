package com.praveen.expportal.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.expportal.models.Departments;
import com.praveen.expportal.models.Expenses;
import com.praveen.expportal.models.User;
import com.praveen.expportal.models.User.Role;
import com.praveen.expportal.repositories.DepartmentRepository;
import com.praveen.expportal.repositories.ExpenseRepository;
import com.praveen.expportal.repositories.UserRepository;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRepository;
    
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;


    
    public boolean createExpenses(Expenses expenses) {
        User emp = userRepository.findByEmployeeId(expenses.getEmployeeId());
        if (emp == null) {
            throw new RuntimeException("Employee not found");
        }

        Departments dept = departmentRepository.findByDepartmentId(emp.getDepartmentId()).get();
        if (dept == null) {
            throw new RuntimeException("Department not found");
        }

        if (expenses.getAmount() > dept.getBudgetLimit()) {
            return false;
        }

       
        
        
        expenses.setStatus(Expenses.Status.PENDING);
        expenses.setSubmissionDate(LocalDate.now());
        expenses.setDepartment(dept);
        expenseRepository.save(expenses);
        dept.setExpenses(expenses);
        departmentRepository.save(dept);

        return true;
    }
    public Expenses updateExpenses(Expenses updatedExpense, Long id) {
        return expenseRepository.findById(id).map(existingExpense -> {
            existingExpense.setEmployeeId(updatedExpense.getEmployeeId());
            existingExpense.setAmount(updatedExpense.getAmount());
            existingExpense.setDescription(updatedExpense.getDescription());
            existingExpense.setExpenseDate(updatedExpense.getExpenseDate());
            existingExpense.setCategory(updatedExpense.getCategory());
            existingExpense.setStatus(updatedExpense.getStatus());
            existingExpense.setRemarks(updatedExpense.getRemarks());
            existingExpense.setSubmissionDate(updatedExpense.getSubmissionDate());
            existingExpense.setApprovalDate(updatedExpense.getApprovalDate());
            existingExpense.setDepartment(updatedExpense.getDepartment());
            return expenseRepository.save(existingExpense);
        }).orElseThrow(() -> new RuntimeException("Expense not found with ID: " + id));
    }

    public List<Expenses> getPendingExpensesByManagerId(String managerId) {
        return expenseRepository.findByDepartmentManagerIdAndStatus(managerId, Expenses.Status.PENDING);
    }
    
    public List<Expenses> getExpensesByEmployeeId(String employeeId) {
        return expenseRepository.findByEmployeeId(employeeId);
    }
    
    public List<Expenses> getExpensesByDepartmentId(Long deptId) {
        return expenseRepository.findByDepartmentId(deptId);
    }

}
