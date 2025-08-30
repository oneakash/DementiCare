package com.dementicare.userservice.controller;

import com.dementicare.userservice.dto.UserPreferencesDto;
import com.dementicare.userservice.dto.UserRegistrationDto;
import com.dementicare.userservice.dto.UserResponseDto;
import com.dementicare.userservice.entity.Role;
import com.dementicare.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegistrationDto registrationDto) {
        UserResponseDto user = userService.registerUser(registrationDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or authentication.name == @userService.getUserById(#id).email")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable String id) {
        UserResponseDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/email/{email}")
    @PreAuthorize("hasRole('ADMIN') or authentication.name == #email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        UserResponseDto user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/role/{role}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    public ResponseEntity<List<UserResponseDto>> getUsersByRole(@PathVariable Role role) {
        List<UserResponseDto> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or authentication.name == @userService.getUserById(#id).email")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable String id, 
            @Valid @RequestBody UserRegistrationDto updateDto) {
        UserResponseDto user = userService.updateUser(id, updateDto);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/{id}/preferences")
    @PreAuthorize("hasRole('ADMIN') or authentication.name == @userService.getUserById(#id).email")
    public ResponseEntity<UserResponseDto> updateUserPreferences(
            @PathVariable String id, 
            @Valid @RequestBody UserPreferencesDto preferencesDto) {
        UserResponseDto user = userService.updateUserPreferences(id, preferencesDto);
        return ResponseEntity.ok(user);
    }
    
    @PutMapping("/{id}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivateUser(@PathVariable String id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> activateUser(@PathVariable String id) {
        userService.activateUser(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/count/{role}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    public ResponseEntity<Long> getUserCountByRole(@PathVariable Role role) {
        long count = userService.getUserCountByRole(role);
        return ResponseEntity.ok(count);
    }
    
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    public ResponseEntity<List<UserResponseDto>> searchUsers(@RequestParam String name) {
        List<UserResponseDto> users = userService.searchUsersByName(name);
        return ResponseEntity.ok(users);
    }
}