package com.dementicare.taskservice.repository;

import com.dementicare.taskservice.entity.Task;
import com.dementicare.taskservice.entity.TaskStatus;
import com.dementicare.taskservice.entity.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    
    List<Task> findByStudentId(String studentId);
    
    List<Task> findByStudentIdAndStatus(String studentId, TaskStatus status);
    
    List<Task> findByStudentIdAndType(String studentId, TaskType type);
    
    List<Task> findByAssignedBy(String assignedBy);
    
    @Query("SELECT t FROM Task t WHERE t.studentId = :studentId AND DATE(t.scheduledDate) = DATE(:date)")
    List<Task> findByStudentIdAndScheduledDate(@Param("studentId") String studentId, @Param("date") LocalDateTime date);
    
    @Query("SELECT t FROM Task t WHERE t.studentId = :studentId AND t.status = :status AND DATE(t.scheduledDate) = DATE(:date)")
    List<Task> findByStudentIdAndStatusAndScheduledDate(
        @Param("studentId") String studentId, 
        @Param("status") TaskStatus status, 
        @Param("date") LocalDateTime date
    );
    
    @Query("SELECT COUNT(t) FROM Task t WHERE t.studentId = :studentId AND t.status = :status")
    long countByStudentIdAndStatus(@Param("studentId") String studentId, @Param("status") TaskStatus status);
    
    @Query("SELECT t FROM Task t WHERE t.scheduledDate BETWEEN :startDate AND :endDate ORDER BY t.scheduledTime")
    List<Task> findTasksInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT t FROM Task t WHERE t.studentId = :studentId AND t.scheduledDate BETWEEN :startDate AND :endDate ORDER BY t.scheduledTime")
    List<Task> findStudentTasksInDateRange(
        @Param("studentId") String studentId,
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );
}