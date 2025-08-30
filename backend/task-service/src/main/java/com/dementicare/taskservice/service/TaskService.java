package com.dementicare.taskservice.service;

import com.dementicare.taskservice.dto.TaskDto;
import com.dementicare.taskservice.dto.TaskResponseDto;
import com.dementicare.taskservice.entity.Task;
import com.dementicare.taskservice.entity.TaskStatus;
import com.dementicare.taskservice.entity.TaskType;
import com.dementicare.taskservice.exception.TaskNotFoundException;
import com.dementicare.taskservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    public TaskResponseDto createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setType(taskDto.getType());
        task.setStatus(taskDto.getStatus());
        task.setScheduledTime(taskDto.getScheduledTime());
        task.setDurationMinutes(taskDto.getDurationMinutes());
        task.setVisualAids(taskDto.getVisualAids());
        task.setStudentId(taskDto.getStudentId());
        task.setAssignedBy(taskDto.getAssignedBy());
        task.setScheduledDate(taskDto.getScheduledDate());
        
        Task savedTask = taskRepository.save(task);
        return new TaskResponseDto(savedTask);
    }
    
    public TaskResponseDto getTaskById(String id) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        return new TaskResponseDto(task);
    }
    
    public List<TaskResponseDto> getTasksByStudentId(String studentId) {
        return taskRepository.findByStudentId(studentId)
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());
    }
    
    public List<TaskResponseDto> getTasksByStudentIdAndStatus(String studentId, TaskStatus status) {
        return taskRepository.findByStudentIdAndStatus(studentId, status)
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());
    }
    
    public List<TaskResponseDto> getTasksByStudentIdAndType(String studentId, TaskType type) {
        return taskRepository.findByStudentIdAndType(studentId, type)
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());
    }
    
    public List<TaskResponseDto> getTodayTasksForStudent(String studentId) {
        LocalDateTime today = LocalDateTime.now();
        return taskRepository.findByStudentIdAndScheduledDate(studentId, today)
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());
    }
    
    public List<TaskResponseDto> getTasksByAssignedBy(String assignedBy) {
        return taskRepository.findByAssignedBy(assignedBy)
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());
    }
    
    public TaskResponseDto updateTask(String id, TaskDto taskDto) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setType(taskDto.getType());
        task.setScheduledTime(taskDto.getScheduledTime());
        task.setDurationMinutes(taskDto.getDurationMinutes());
        task.setVisualAids(taskDto.getVisualAids());
        task.setScheduledDate(taskDto.getScheduledDate());
        
        Task updatedTask = taskRepository.save(task);
        return new TaskResponseDto(updatedTask);
    }
    
    public TaskResponseDto updateTaskStatus(String id, TaskStatus status) {
        Task task = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        
        task.setStatus(status);
        Task updatedTask = taskRepository.save(task);
        return new TaskResponseDto(updatedTask);
    }
    
    public void deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }
    
    public long getCompletedTasksCount(String studentId) {
        return taskRepository.countByStudentIdAndStatus(studentId, TaskStatus.COMPLETED);
    }
    
    public long getTotalTasksCount(String studentId) {
        return taskRepository.findByStudentId(studentId).size();
    }
    
    public List<TaskResponseDto> getTasksInDateRange(String studentId, LocalDateTime startDate, LocalDateTime endDate) {
        return taskRepository.findStudentTasksInDateRange(studentId, startDate, endDate)
            .stream()
            .map(TaskResponseDto::new)
            .collect(Collectors.toList());
    }
}