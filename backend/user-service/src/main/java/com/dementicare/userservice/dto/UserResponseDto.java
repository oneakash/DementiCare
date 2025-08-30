package com.dementicare.userservice.dto;

import com.dementicare.userservice.entity.Role;
import com.dementicare.userservice.entity.User;

import java.time.LocalDateTime;

public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private Role role;
    private String avatarUrl;
    private String preferredLanguage;
    private String fontSize;
    private Boolean highContrast;
    private Boolean audioEnabled;
    private Boolean notificationsEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;
    
    // Constructors
    public UserResponseDto() {}
    
    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.avatarUrl = user.getAvatarUrl();
        this.preferredLanguage = user.getPreferredLanguage();
        this.fontSize = user.getFontSize();
        this.highContrast = user.getHighContrast();
        this.audioEnabled = user.getAudioEnabled();
        this.notificationsEnabled = user.getNotificationsEnabled();
        this.createdAt = user.getCreatedAt();
        this.lastLogin = user.getLastLogin();
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    
    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
    
    public String getPreferredLanguage() { return preferredLanguage; }
    public void setPreferredLanguage(String preferredLanguage) { this.preferredLanguage = preferredLanguage; }
    
    public String getFontSize() { return fontSize; }
    public void setFontSize(String fontSize) { this.fontSize = fontSize; }
    
    public Boolean getHighContrast() { return highContrast; }
    public void setHighContrast(Boolean highContrast) { this.highContrast = highContrast; }
    
    public Boolean getAudioEnabled() { return audioEnabled; }
    public void setAudioEnabled(Boolean audioEnabled) { this.audioEnabled = audioEnabled; }
    
    public Boolean getNotificationsEnabled() { return notificationsEnabled; }
    public void setNotificationsEnabled(Boolean notificationsEnabled) { this.notificationsEnabled = notificationsEnabled; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
}