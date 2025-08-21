package com.praveen.expportal.controllers;

import com.praveen.expportal.models.Departments;
import com.praveen.expportal.models.Expenses;
import com.praveen.expportal.services.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/without-manager")
    public List<Departments> getDepartmentsWithoutManager() {
        return departmentService.getDepartmentsWithoutManager();
    }
    @PostMapping
    public ResponseEntity<Departments> createDepartment(@RequestBody Departments department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Departments> updateDepartment(@PathVariable Long id, @RequestBody Departments updatedDept) {
        return ResponseEntity.ok(departmentService.updateDepartment(id, updatedDept));
    }


    @GetMapping
    public ResponseEntity<List<Departments>> getAllDepartments() {
        try{
            return ResponseEntity.ok(departmentService.getAllDepartments());
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Departments> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }


    @GetMapping("/by-deptid/{departmentId}")
    public ResponseEntity<Departments> getByDepartmentId(@PathVariable String departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentsbyDeptId(departmentId));
    }

 
    @GetMapping("/{id}/expenses")
    public ResponseEntity<List<Expenses>> getExpensesByDepartmentId(@PathVariable String id) {
        return ResponseEntity.ok(departmentService.getExpensesByDepartmentId(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
