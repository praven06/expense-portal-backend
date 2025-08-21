package com.praveen.expportal.controllers;

import com.praveen.expportal.models.ExpensePolicy;
import com.praveen.expportal.services.ExpensePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/policies")
public class ExpensePolicyController {

    @Autowired
    private ExpensePolicyService policyService;

    @PostMapping
    public ExpensePolicy createPolicy(@RequestBody ExpensePolicy policy) {
        return policyService.createPolicy(policy);
    }

    @GetMapping
    public List<ExpensePolicy> getAllPolicies() {
        return policyService.getAllPolicies();
    }

    @GetMapping("/{id}")
    public ExpensePolicy getPolicyById(@PathVariable Long id) {
        return policyService.getPolicyById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<ExpensePolicy> getPoliciesByCategory(@PathVariable Long categoryId) {
        return policyService.getPoliciesByCategoryId(categoryId);
    }

    @DeleteMapping("/{id}")
    public void deletePolicy(@PathVariable Long id) {
        policyService.deletePolicy(id);
    }
}
