package com.dementicare.taskservice.dto;

import com.dementicare.taskservice.entity.TaskStatus;
import com.dementicare.taskservice.entity.TaskType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class TaskDto {
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private String description;
    
    @NotNull(message = "Task type is required")
    private TaskType type;
    
    private TaskStatus status = TaskStatus.PENDING;
    
    private LocalTime scheduledTime;
    
    private Integer durationMinutes;
    
    private List<String> visualAids;
    
    @NotBlank(message = "Student ID is required")
    private String studentId;
    
    private String assignedBy;
    
    private LocalDateTime scheduledDate;
    
    // Constructors
    public TaskDto() {}
    
    // Getters and Setters
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
    
    public LocalDateTime getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDateTime scheduledDate) { this.scheduledDate = scheduledDate; }
}