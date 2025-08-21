package com.praveen.expportal.repositories;

import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.praveen.expportal.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmployeeId(String id);
    boolean existsByUsername(String username);

    List<User> findByRole(Role role);
    @Query("SELECT u FROM User u WHERE u.role = 'MANAGER' AND u.departmentId IS NULL ")
    List<User> findManagersWithoutDepartment();

    User findUserByEmployeeId(String employee_id);

}
