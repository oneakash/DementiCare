package com.dementicare.userservice.dto;

public class UserPreferencesDto {
    private String preferredLanguage;
    private String fontSize;
    private Boolean highContrast;
    private Boolean audioEnabled;
    private Boolean notificationsEnabled;
    
    // Constructors
    public UserPreferencesDto() {}
    
    // Getters and Setters
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
}