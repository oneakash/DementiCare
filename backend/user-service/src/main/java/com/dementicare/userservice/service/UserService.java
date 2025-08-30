package com.dementicare.userservice.service;

import com.dementicare.userservice.dto.UserPreferencesDto;
import com.dementicare.userservice.dto.UserRegistrationDto;
import com.dementicare.userservice.dto.UserResponseDto;
import com.dementicare.userservice.entity.Role;
import com.dementicare.userservice.entity.User;
import com.dementicare.userservice.exception.UserAlreadyExistsException;
import com.dementicare.userservice.exception.UserNotFoundException;
import com.dementicare.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public UserResponseDto registerUser(UserRegistrationDto registrationDto) {
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            throw new UserAlreadyExistsException("User with email " + registrationDto.getEmail() + " already exists");
        }
        
        User user = new User();
        user.setName(registrationDto.getName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        user.setRole(registrationDto.getRole());
        user.setPreferredLanguage(registrationDto.getPreferredLanguage());
        
        User savedUser = userRepository.save(user);
        return new UserResponseDto(savedUser);
    }
    
    public UserResponseDto getUserById(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return new UserResponseDto(user);
    }
    
    public UserResponseDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
        return new UserResponseDto(user);
    }
    
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findByIsActiveTrue()
            .stream()
            .map(UserResponseDto::new)
            .collect(Collectors.toList());
    }
    
    public List<UserResponseDto> getUsersByRole(Role role) {
        return userRepository.findActiveUsersByRole(role)
            .stream()
            .map(UserResponseDto::new)
            .collect(Collectors.toList());
    }
    
    public UserResponseDto updateUser(String id, UserRegistrationDto updateDto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        
        user.setName(updateDto.getName());
        user.setPreferredLanguage(updateDto.getPreferredLanguage());
        
        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser);
    }
    
    public UserResponseDto updateUserPreferences(String id, UserPreferencesDto preferencesDto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        
        user.setFontSize(preferencesDto.getFontSize());
        user.setHighContrast(preferencesDto.getHighContrast());
        user.setAudioEnabled(preferencesDto.getAudioEnabled());
        user.setNotificationsEnabled(preferencesDto.getNotificationsEnabled());
        user.setPreferredLanguage(preferencesDto.getPreferredLanguage());
        
        User updatedUser = userRepository.save(user);
        return new UserResponseDto(updatedUser);
    }
    
    public void updateLastLogin(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
    }
    
    public void deactivateUser(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setIsActive(false);
        userRepository.save(user);
    }
    
    public void activateUser(String id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setIsActive(true);
        userRepository.save(user);
    }
    
    public long getUserCountByRole(Role role) {
        return userRepository.countActiveUsersByRole(role);
    }
    
    public List<UserResponseDto> searchUsersByName(String name) {
        return userRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(name)
            .stream()
            .map(UserResponseDto::new)
            .collect(Collectors.toList());
    }
}