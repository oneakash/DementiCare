package com.dementicare.userservice.service;

import com.dementicare.userservice.dto.AuthResponseDto;
import com.dementicare.userservice.dto.UserLoginDto;
import com.dementicare.userservice.dto.UserResponseDto;
import com.dementicare.userservice.entity.User;
import com.dementicare.userservice.exception.InvalidCredentialsException;
import com.dementicare.userservice.repository.UserRepository;
import com.dementicare.userservice.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private UserService userService;
    
    public AuthResponseDto authenticateUser(UserLoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
            .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));
        
        if (!user.getIsActive()) {
            throw new InvalidCredentialsException("Account is deactivated");
        }
        
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        
        // Update last login
        userService.updateLastLogin(user.getId());
        
        String token = jwtTokenProvider.generateToken(user);
        UserResponseDto userResponse = new UserResponseDto(user);
        
        return new AuthResponseDto(token, userResponse);
    }
    
    public String refreshToken(String token) {
        if (jwtTokenProvider.validateToken(token)) {
            String email = jwtTokenProvider.getEmailFromToken(token);
            User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));
            return jwtTokenProvider.generateToken(user);
        }
        throw new InvalidCredentialsException("Invalid token");
    }
}