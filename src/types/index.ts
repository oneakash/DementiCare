export interface User {
  id: string;
  email: string;
  name: string;
  role: 'student' | 'caregiver' | 'admin';
  avatar?: string;
  preferences?: UserPreferences;
  createdAt: string;
}

export interface UserPreferences {
  language: string;
  fontSize: 'small' | 'medium' | 'large';
  highContrast: boolean;
  audioEnabled: boolean;
  notificationsEnabled: boolean;
}

export interface Task {
  id: string;
  title: string;
  description?: string;
  type: 'visual_schedule' | 'flashcard' | 'social_story' | 'sensory_break';
  status: 'pending' | 'in_progress' | 'completed';
  scheduledTime?: string;
  duration?: number;
  visualAids: string[];
  assignedBy?: string;
  studentId: string;
  createdAt: string;
  completedAt?: string;
}

export interface Flashcard {
  id: string;
  front: string;
  back: string;
  image?: string;
  audio?: string;
  category: string;
  difficulty: 'easy' | 'medium' | 'hard';
  repetitionLevel: number;
  nextReview: string;
  isCustom: boolean;
  createdBy: string;
}

export interface Achievement {
  id: string;
  title: string;
  description: string;
  icon: string;
  type: 'daily' | 'weekly' | 'milestone';
  points: number;
  unlockedAt?: string;
}

export interface MoodEntry {
  id: string;
  mood: 'happy' | 'sad' | 'calm' | 'excited' | 'frustrated' | 'tired';
  intensity: number;
  notes?: string;
  timestamp: string;
  userId: string;
}

export interface SocialStory {
  id: string;
  title: string;
  scenario: string;
  steps: StoryStep[];
  category: string;
  difficulty: 'beginner' | 'intermediate' | 'advanced';
  createdBy: string;
  isPublic: boolean;
}

export interface StoryStep {
  id: string;
  text: string;
  image?: string;
  audio?: string;
  order: number;
}