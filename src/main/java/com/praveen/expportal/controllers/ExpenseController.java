package com.praveen.expportal.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.praveen.expportal.models.ExpenseCategory;
import com.praveen.expportal.models.Expenses;
import com.praveen.expportal.services.ExpenseCategoryService;
import com.praveen.expportal.services.ExpenseService;

@RestController
@RequestMapping("/api/expense")
public class ExpenseController {
    @Autowired
    private ExpenseCategoryService expenseCategoryService;
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(expenseService.getAllExpenses(page, size));
    }

    
    @PostMapping("/{id}")
    ResponseEntity<?> creatExpenses(@PathVariable Long id,@RequestBody Expenses expense) {
        ExpenseCategory exp = expenseCategoryService.getExpenseCategoryById(id);
        if (exp == null) {
            return ResponseEntity.badRequest().body("Category not found");
        }
        expense.setCategory(exp);
        expense.setSubmissionDate(LocalDate.now());
        if (expenseService.createExpenses(expense))
            return ResponseEntity.ok().body(expense);
        else
            return ResponseEntity.badRequest().build();
    }
    @GetMapping("/{employee_id}")
    public ResponseEntity<?> getExpensesByEmployeeId(
            @PathVariable("employee_id") String employeeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(expenseService.getExpensesByEmployeeId(employeeId, page, size));
    }
    
}
