package com.dementicare.contentservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "flashcards")
public class Flashcard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    
    @NotBlank(message = "Front content is required")
    @Column(nullable = false, length = 500)
    private String front;
    
    @NotBlank(message = "Back content is required")
    @Column(nullable = false, length = 500)
    private String back;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column(name = "audio_url")
    private String audioUrl;
    
    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    private String category;
    
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Difficulty is required")
    @Column(nullable = false)
    private Difficulty difficulty;
    
    @Column(name = "repetition_level", nullable = false)
    private Integer repetitionLevel = 0;
    
    @Column(name = "next_review")
    private LocalDateTime nextReview;
    
    @Column(name = "is_custom", nullable = false)
    private Boolean isCustom = false;
    
    @NotBlank(message = "Created by is required")
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    
    @Column(name = "student_id")
    private String studentId;
    
    @Column(name = "success_count", nullable = false)
    private Integer successCount = 0;
    
    @Column(name = "failure_count", nullable = false)
    private Integer failureCount = 0;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (nextReview == null) {
            nextReview = LocalDateTime.now().plusDays(1);
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // Constructors
    public Flashcard() {}
    
    public Flashcard(String front, String back, String category, Difficulty difficulty, String createdBy) {
        this.front = front;
        this.back = back;
        this.category = category;
        this.difficulty = difficulty;
        this.createdBy = createdBy;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getFront() { return front; }
    public void setFront(String front) { this.front = front; }
    
    public String getBack() { return back; }
    public void setBack(String back) { this.back = back; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }
    
    public Integer getRepetitionLevel() { return repetitionLevel; }
    public void setRepetitionLevel(Integer repetitionLevel) { this.repetitionLevel = repetitionLevel; }
    
    public LocalDateTime getNextReview() { return nextReview; }
    public void setNextReview(LocalDateTime nextReview) { this.nextReview = nextReview; }
    
    public Boolean getIsCustom() { return isCustom; }
    public void setIsCustom(Boolean isCustom) { this.isCustom = isCustom; }
    
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public Integer getSuccessCount() { return successCount; }
    public void setSuccessCount(Integer successCount) { this.successCount = successCount; }
    
    public Integer getFailureCount() { return failureCount; }
    public void setFailureCount(Integer failureCount) { this.failureCount = failureCount; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}