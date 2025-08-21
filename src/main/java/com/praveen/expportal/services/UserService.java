package com.praveen.expportal.services;

import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.praveen.expportal.models.User;
import com.praveen.expportal.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findByEmployeeId(id);
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    public User createUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }
    
    public List<User> getManagersWithoutDepartment() {
        return userRepository.findManagersWithoutDepartment();
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());

                    if (!updatedUser.getPasswordHash().equals(user.getPasswordHash())) {
                        user.setPasswordHash(passwordEncoder.encode(updatedUser.getPasswordHash()));
                    }

                    user.setRole(updatedUser.getRole());
                    user.setEmployeeId(updatedUser.getEmployeeId());
                    user.setDepartmentId(updatedUser.getDepartmentId());
                    user.setManagerId(updatedUser.getManagerId());
                    user.setCreatedDate(updatedUser.getCreatedDate());
                    user.setLastLogin(updatedUser.getLastLogin());
                    user.setActive(updatedUser.isActive());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }
    
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    public User getUserByEmployeeId(String id) {
        User user = userRepository.findByEmployeeId(id);
        return user;

    }

}
