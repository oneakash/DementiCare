package com.dementicare.contentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "social_stories")
public class SocialStory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;
    
    @NotBlank(message = "Scenario is required")
    @Column(nullable = false, length = 2000)
    private String scenario;
    
    @OneToMany(mappedBy = "socialStory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("stepOrder ASC")
    private List<StoryStep> steps;
    
    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Difficulty is required")
    @Column(nullable = false)
    private Difficulty difficulty;
    
    @NotBlank(message = "Created by is required")
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;
    
    @Column(name = "language", nullable = false)
    private String language = "en";
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public SocialStory() {}
    
    public SocialStory(String title, String scenario, String category, Difficulty difficulty, String createdBy) {
        this.title = title;
        this.scenario = scenario;
        this.category = category;
        this.difficulty = difficulty;
        this.createdBy = createdBy;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getScenario() { return scenario; }
    public void setScenario(String scenario) { this.scenario = scenario; }
    
    public List<StoryStep> getSteps() { return steps; }
    public void setSteps(List<StoryStep> steps) { this.steps = steps; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
    
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    
    public Boolean getIsPublic() { return isPublic; }
    public void setIsPublic(Boolean isPublic) { this.isPublic = isPublic; }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}