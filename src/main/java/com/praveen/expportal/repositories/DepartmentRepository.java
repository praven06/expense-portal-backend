package com.praveen.expportal.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.praveen.expportal.models.Departments;

public interface DepartmentRepository extends JpaRepository<Departments, Long> {
    
    Optional<Departments> findByDepartmentId(String id);
    @Query("SELECT d FROM Departments d WHERE d.managerId IS NULL")
    List<Departments> findDepartmentsWithoutManager();
}
