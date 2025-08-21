package com.praveen.expportal.controllers;

import com.praveen.expportal.models.ExpenseCategory;
import com.praveen.expportal.services.ExpenseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService categoryService;

    @PostMapping
    public ExpenseCategory createCategory(@RequestBody ExpenseCategory category) {
        return categoryService.createCategory(category);
    }

    @GetMapping
    public List<ExpenseCategory> getAllCategories() {
        return categoryService.getAllCategories();
    }


    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
