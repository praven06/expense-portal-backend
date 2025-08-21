package com.praveen.expportal.services;

import com.praveen.expportal.models.ExpensePolicy;
import com.praveen.expportal.repositories.ExpensepolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpensePolicyService {

    @Autowired
    private ExpensepolicyRepository policyRepository;

    public ExpensePolicy createPolicy(ExpensePolicy policy) {
        return policyRepository.save(policy);
    }

    public List<ExpensePolicy> getAllPolicies() {
        return policyRepository.findAll();
    }

    public ExpensePolicy getPolicyById(Long id) {
        return policyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense policy not found with ID: " + id));
    }

    public List<ExpensePolicy> getPoliciesByCategoryId(Long categoryId) {
        return policyRepository.findByCategoryId(categoryId);
    }

    public void deletePolicy(Long id) {
        if (!policyRepository.existsById(id)) {
            throw new RuntimeException("Expense policy not found with ID: " + id);
        }
        policyRepository.deleteById(id);
    }
}
