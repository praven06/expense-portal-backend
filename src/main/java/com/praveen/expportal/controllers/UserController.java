package com.praveen.expportal.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.praveen.expportal.jwt.JWTService;
import com.praveen.expportal.models.Approval;
import com.praveen.expportal.models.Expenses;
import com.praveen.expportal.models.User;
import com.praveen.expportal.repositories.ExpenseRepository;
import com.praveen.expportal.repositories.UserRepository;
import com.praveen.expportal.services.ApprovalService;
import com.praveen.expportal.services.ExpenseService;
import com.praveen.expportal.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final ExpenseService expenseService;

    @Autowired
    private final ApprovalService approvalService;
    
    @Autowired
    private final UserService userService;

    @Autowired
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private final JWTService jwtService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ExpenseRepository expenseRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/managers/without-department")
    public ResponseEntity<List<User>> getManagersWithoutDepartment() {
        List<User> managers = userService.getManagersWithoutDepartment();
        return ResponseEntity.ok(managers);
    }   


    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        User user = userService.getUserByEmployeeId(id);
        
        if(user == null || user.isActive()==false){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/approve")
    public ResponseEntity<?> updateExpenses(@RequestBody Approval approval) {
        Optional<Expenses> optionalExpense = expenseRepository.findById(approval.getExpenseId());

        if (optionalExpense.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
        }

        Expenses expense = optionalExpense.get();
        if (!expense.getDepartment().getManagerId().equals(approval.getApproverId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You are not authorized to approve this expense");
        }

        expense.setStatus(approval.getApprovalStatus());
        expense.setApproval(approval);
        expense.setApprovalDate(LocalDate.now());

        try {
            approvalService.createApproval(approval);
            expenseService.updateExpenses(expense, approval.getExpenseId());
            return ResponseEntity.ok(expense);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while approving expense: " + e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        user.setActive(true);
        user.setCreatedDate(LocalDate.now());
        String token = jwtService.generateToken(user);
        userService.createUser(user);
        return ResponseEntity.ok(token);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        userRepository.delete(user.get());
        return ResponseEntity.noContent().build();
    }

    
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> req) {
        String employee_id = req.get("employee_id");
        String password = req.get("password");
        User user = userRepository.findByEmployeeId(employee_id);

        if (user == null || !passwordEncoder.matches(password, user.getPasswordHash())) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
        user.setLastLogin(LocalDate.now());
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
