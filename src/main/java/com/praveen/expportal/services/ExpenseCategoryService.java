package com.praveen.expportal.services;

import com.praveen.expportal.models.ExpenseCategory;
import com.praveen.expportal.repositories.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryService {

    @Autowired
    private ExpenseCategoryRepository categoryRepository;

    public ExpenseCategory getExpenseCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense Category not found with ID: " + id));
    }
    public ExpenseCategory createCategory(ExpenseCategory category) {
        return categoryRepository.save(category);
    }

    public List<ExpenseCategory> getAllCategories() {
        return categoryRepository.findAll();
    }


    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found with ID: " + id);
        }
        categoryRepository.deleteById(id);
    }
}
