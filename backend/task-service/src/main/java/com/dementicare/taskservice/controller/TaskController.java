package com.dementicare.taskservice.controller;

import com.dementicare.taskservice.dto.TaskDto;
import com.dementicare.taskservice.dto.TaskResponseDto;
import com.dementicare.taskservice.entity.TaskStatus;
import com.dementicare.taskservice.entity.TaskType;
import com.dementicare.taskservice.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @PostMapping
    @PreAuthorize("hasRole('CAREGIVER') or hasRole('ADMIN')")
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskDto taskDto) {
        TaskResponseDto task = taskService.createTask(taskDto);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER') or @taskService.getTaskById(#id).studentId == authentication.principal.id")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable String id) {
        TaskResponseDto task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    
    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER') or #studentId == authentication.principal.id")
    public ResponseEntity<List<TaskResponseDto>> getTasksByStudentId(@PathVariable String studentId) {
        List<TaskResponseDto> tasks = taskService.getTasksByStudentId(studentId);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/student/{studentId}/status/{status}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER') or #studentId == authentication.principal.id")
    public ResponseEntity<List<TaskResponseDto>> getTasksByStudentIdAndStatus(
            @PathVariable String studentId, 
            @PathVariable TaskStatus status) {
        List<TaskResponseDto> tasks = taskService.getTasksByStudentIdAndStatus(studentId, status);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/student/{studentId}/type/{type}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER') or #studentId == authentication.principal.id")
    public ResponseEntity<List<TaskResponseDto>> getTasksByStudentIdAndType(
            @PathVariable String studentId, 
            @PathVariable TaskType type) {
        List<TaskResponseDto> tasks = taskService.getTasksByStudentIdAndType(studentId, type);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/student/{studentId}/today")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER') or #studentId == authentication.principal.id")
    public ResponseEntity<List<TaskResponseDto>> getTodayTasksForStudent(@PathVariable String studentId) {
        List<TaskResponseDto> tasks = taskService.getTodayTasksForStudent(studentId);
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping("/assigned-by/{assignedBy}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    public ResponseEntity<List<TaskResponseDto>> getTasksByAssignedBy(@PathVariable String assignedBy) {
        List<TaskResponseDto> tasks = taskService.getTasksByAssignedBy(assignedBy);
        return ResponseEntity.ok(tasks);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable String id, 
            @Valid @RequestBody TaskDto taskDto) {
        TaskResponseDto task = taskService.updateTask(id, taskDto);
        return ResponseEntity.ok(task);
    }
    
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER') or @taskService.getTaskById(#id).studentId == authentication.principal.id")
    public ResponseEntity<TaskResponseDto> updateTaskStatus(
            @PathVariable String id, 
            @RequestParam TaskStatus status) {
        TaskResponseDto task = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(task);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER')")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/student/{studentId}/progress")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER') or #studentId == authentication.principal.id")
    public ResponseEntity<TaskProgressDto> getTaskProgress(@PathVariable String studentId) {
        long completedTasks = taskService.getCompletedTasksCount(studentId);
        long totalTasks = taskService.getTotalTasksCount(studentId);
        
        TaskProgressDto progress = new TaskProgressDto();
        progress.setCompletedTasks(completedTasks);
        progress.setTotalTasks(totalTasks);
        progress.setProgressPercentage(totalTasks > 0 ? (double) completedTasks / totalTasks * 100 : 0);
        
        return ResponseEntity.ok(progress);
    }
    
    @GetMapping("/student/{studentId}/range")
    @PreAuthorize("hasRole('ADMIN') or hasRole('CAREGIVER') or #studentId == authentication.principal.id")
    public ResponseEntity<List<TaskResponseDto>> getTasksInDateRange(
            @PathVariable String studentId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<TaskResponseDto> tasks = taskService.getTasksInDateRange(studentId, startDate, endDate);
        return ResponseEntity.ok(tasks);
    }
    
    public static class TaskProgressDto {
        private long completedTasks;
        private long totalTasks;
        private double progressPercentage;
        
        // Getters and Setters
        public long getCompletedTasks() { return completedTasks; }
        public void setCompletedTasks(long completedTasks) { this.completedTasks = completedTasks; }
        
        public long getTotalTasks() { return totalTasks; }
        public void setTotalTasks(long totalTasks) { this.totalTasks = totalTasks; }
        
        public double getProgressPercentage() { return progressPercentage; }
        public void setProgressPercentage(double progressPercentage) { this.progressPercentage = progressPercentage; }
    }
}