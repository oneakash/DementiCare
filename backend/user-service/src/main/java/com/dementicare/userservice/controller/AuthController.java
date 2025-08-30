package com.dementicare.userservice.controller;

import com.dementicare.userservice.dto.AuthResponseDto;
import com.dementicare.userservice.dto.UserLoginDto;
import com.dementicare.userservice.dto.UserRegistrationDto;
import com.dementicare.userservice.dto.UserResponseDto;
import com.dementicare.userservice.service.AuthService;
import com.dementicare.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private AuthService authService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody UserLoginDto loginDto) {
        AuthResponseDto authResponse = authService.authenticateUser(loginDto);
        return ResponseEntity.ok(authResponse);
    }
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody UserRegistrationDto registrationDto) {
        UserResponseDto user = userService.registerUser(registrationDto);
        UserLoginDto loginDto = new UserLoginDto(registrationDto.getEmail(), registrationDto.getPassword());
        AuthResponseDto authResponse = authService.authenticateUser(loginDto);
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refreshToken(@RequestHeader("Authorization") String token) {
        String refreshedToken = authService.refreshToken(token.substring(7)); // Remove "Bearer " prefix
        AuthResponseDto authResponse = new AuthResponseDto();
        authResponse.setToken(refreshedToken);
        return ResponseEntity.ok(authResponse);
    }
    
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        // In a production environment, you might want to blacklist the token
        return ResponseEntity.ok().build();
    }
}