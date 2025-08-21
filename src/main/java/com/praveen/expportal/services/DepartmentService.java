package com.praveen.expportal.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praveen.expportal.models.Departments;
import com.praveen.expportal.models.Expenses;
import com.praveen.expportal.models.User;
import com.praveen.expportal.repositories.DepartmentRepository;
import com.praveen.expportal.repositories.UserRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;
    public Departments createDepartment(Departments department) {
        Departments dept = departmentRepository.save(department);
        if (department.getManagerId() != null) {
            User user = userRepository.findUserByEmployeeId(department.getManagerId());
            if (user != null) {
                user.setDepartmentId(dept.getDepartmentId());
                userRepository.save(user);
            }
        }
        return dept;
    }

   
    public Departments updateDepartment(Long id, Departments updatedDept) {
        Departments existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        existing.setDepartmentId(updatedDept.getDepartmentId());
        existing.setDepartmentName(updatedDept.getDepartmentName());
        existing.setDepartmentCode(updatedDept.getDepartmentCode());
        existing.setManagerId(updatedDept.getManagerId());
        existing.setBudgetLimit(updatedDept.getBudgetLimit());

        return departmentRepository.save(existing);
    }

    
    public List<Departments> getAllDepartments() {
        return departmentRepository.findAll();
    }

    
    public Departments getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public Departments getDepartmentsbyDeptId(String id) {
        return departmentRepository.findByDepartmentId(id).get();
    }


    public List<Expenses> getExpensesByDepartmentId(String id) {
         Departments dept = departmentRepository.findByDepartmentId(id)
            .orElseThrow(() -> new RuntimeException("Department not found"));
    
    return dept.getTransactions().stream()
            .filter(expense -> expense.getStatus() == Expenses.Status.PENDING)
            .collect(Collectors.toList());
    }


    public void deleteDepartment(Long id) {
        Departments department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentRepository.delete(department);
    }
    
    public List<Departments> getDepartmentsWithoutManager() {
        return departmentRepository.findDepartmentsWithoutManager();
    }

}
