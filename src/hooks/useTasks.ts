import { useState, useEffect } from 'react';
import { Task } from '../types';

export const useTasks = (userId?: string) => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    // Mock data - replace with actual microservice calls
    const mockTasks: Task[] = [
      {
        id: 'task_1',
        title: 'Morning Routine',
        description: 'Complete your morning activities',
        type: 'visual_schedule',
        status: 'pending',
        scheduledTime: '08:00',
        duration: 30,
        visualAids: ['🌅', '🪥', '🍳'],
        studentId: userId || '',
        createdAt: new Date().toISOString(),
      },
      {
        id: 'task_2',
        title: 'Memory Cards',
        description: 'Practice with daily objects flashcards',
        type: 'flashcard',
        status: 'in_progress',
        duration: 15,
        visualAids: ['🏠', '🚗', '🌳'],
        studentId: userId || '',
        createdAt: new Date().toISOString(),
      },
      {
        id: 'task_3',
        title: 'Social Story: Going Shopping',
        description: 'Learn about shopping at the store',
        type: 'social_story',
        status: 'completed',
        duration: 20,
        visualAids: ['🛒', '💳', '🏪'],
        studentId: userId || '',
        createdAt: new Date().toISOString(),
        completedAt: new Date().toISOString(),
      },
    ];

    setTimeout(() => {
      setTasks(mockTasks);
      setIsLoading(false);
    }, 1000);
  }, [userId]);

  const updateTaskStatus = (taskId: string, status: Task['status']) => {
    setTasks(prev => prev.map(task => 
      task.id === taskId 
        ? { ...task, status, completedAt: status === 'completed' ? new Date().toISOString() : undefined }
        : task
    ));
  };

  return { tasks, isLoading, updateTaskStatus };
};