package com.dementicare.taskservice.dto;

import com.dementicare.taskservice.entity.Task;
import com.dementicare.taskservice.entity.TaskStatus;
import com.dementicare.taskservice.entity.TaskType;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TaskResponseDto {
    private String id;
    private String title;
    private String description;
    private TaskType type;
    private TaskStatus status;
    private LocalTime scheduledTime;
    private Integer durationMinutes;
    private List<String> visualAids;
    private String studentId;
    private String assignedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime completedAt;
    private LocalDateTime scheduledDate;
    
    // Constructors
    public TaskResponseDto() {}
    
    public TaskResponseDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.type = task.getType();
        this.status = task.getStatus();
        this.scheduledTime = task.getScheduledTime();
        this.durationMinutes = task.getDurationMinutes();
        this.visualAids = task.getVisualAids();
        this.studentId = task.getStudentId();
        this.assignedBy = task.getAssignedBy();
        this.createdAt = task.getCreatedAt();
        this.updatedAt = task.getUpdatedAt();
        this.completedAt = task.getCompletedAt();
        this.scheduledDate = task.getScheduledDate();
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public TaskType getType() { return type; }
    public void setType(TaskType type) { this.type = type; }
    
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
    
    public LocalTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalTime scheduledTime) { this.scheduledTime = scheduledTime; }
    
    public Integer getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Integer durationMinutes) { this.durationMinutes = durationMinutes; }
    
    public List<String> getVisualAids() { return visualAids; }
    public void setVisualAids(List<String> visualAids) { this.visualAids = visualAids; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getAssignedBy() { return assignedBy; }
    public void setAssignedBy(String assignedBy) { this.assignedBy = assignedBy; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
    
    public LocalDateTime getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDateTime scheduledDate) { this.scheduledDate = scheduledDate; }
}