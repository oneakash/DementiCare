import React from 'react';
import { useAuth } from '../../context/AuthContext';
import { useTasks } from '../../hooks/useTasks';
import TaskCard from './TaskCard';
import WelcomeCard from './WelcomeCard';
import ProgressCard from './ProgressCard';
import QuickActions from './QuickActions';
import {
  CalendarDaysIcon,
  BookOpenIcon,
  TrophyIcon,
  HeartIcon,
} from '@heroicons/react/24/outline';

const StudentDashboard: React.FC = () => {
  const { user } = useAuth();
  const { tasks, isLoading, updateTaskStatus } = useTasks(user?.id);

  const todayTasks = tasks.filter(task => {
    const today = new Date().toDateString();
    const taskDate = new Date(task.createdAt).toDateString();
    return today === taskDate;
  });

  const completedTasks = tasks.filter(task => task.status === 'completed').length;
  const totalTasks = tasks.length;
  const progressPercentage = totalTasks > 0 ? (completedTasks / totalTasks) * 100 : 0;

  const quickActions = [
    {
      title: 'Visual Schedule',
      description: 'See today\'s activities',
      icon: CalendarDaysIcon,
      href: '/schedule',
      color: 'bg-blue-500',
    },
    {
      title: 'Flashcards',
      description: 'Practice with cards',
      icon: BookOpenIcon,
      href: '/flashcards',
      color: 'bg-green-500',
    },
    {
      title: 'Achievements',
      description: 'View your badges',
      icon: TrophyIcon,
      href: '/achievements',
      color: 'bg-yellow-500',
    },
    {
      title: 'Mood Check',
      description: 'How are you feeling?',
      icon: HeartIcon,
      href: '/mood',
      color: 'bg-pink-500',
    },
  ];

  return (
    <div className="p-6 space-y-6">
      <WelcomeCard user={user} />
      
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div className="lg:col-span-2 space-y-6">
          <div>
            <h3 className="text-xl font-semibold text-slate-800 mb-4">Today's Tasks</h3>
            {isLoading ? (
              <div className="space-y-3">
                {[1, 2, 3].map(i => (
                  <div key={i} className="bg-slate-100 rounded-xl h-24 animate-pulse" />
                ))}
              </div>
            ) : todayTasks.length > 0 ? (
              <div className="space-y-3">
                {todayTasks.map(task => (
                  <TaskCard
                    key={task.id}
                    task={task}
                    onStatusChange={updateTaskStatus}
                  />
                ))}
              </div>
            ) : (
              <div className="bg-slate-50 rounded-xl p-8 text-center">
                <CalendarDaysIcon className="w-12 h-12 text-slate-400 mx-auto mb-3" />
                <p className="text-slate-600">No tasks scheduled for today</p>
                <p className="text-sm text-slate-500 mt-1">Great job staying on top of everything!</p>
              </div>
            )}
          </div>

          <QuickActions actions={quickActions} />
        </div>

        <div className="space-y-6">
          <ProgressCard
            title="Daily Progress"
            progress={progressPercentage}
            subtitle={`${completedTasks} of ${totalTasks} tasks completed`}
          />

          <div className="bg-gradient-to-br from-purple-500 to-pink-500 rounded-xl p-6 text-white">
            <h4 className="font-semibold mb-2">🎯 Daily Goal</h4>
            <p className="text-purple-100 text-sm mb-4">
              Complete 3 learning activities today
            </p>
            <div className="bg-white/20 rounded-full h-2">
              <div 
                className="bg-white rounded-full h-2 transition-all duration-500"
                style={{ width: `${Math.min((completedTasks / 3) * 100, 100)}%` }}
              />
            </div>
            <p className="text-xs text-purple-100 mt-2">
              {Math.min(completedTasks, 3)}/3 completed
            </p>
          </div>

          <div className="bg-white rounded-xl border border-slate-200 p-6">
            <h4 className="font-semibold text-slate-800 mb-3">Recent Achievements</h4>
            <div className="space-y-3">
              <div className="flex items-center space-x-3">
                <div className="w-8 h-8 bg-yellow-100 rounded-full flex items-center justify-center">
                  <span className="text-lg">🏆</span>
                </div>
                <div>
                  <p className="text-sm font-medium text-slate-800">First Task Complete!</p>
                  <p className="text-xs text-slate-500">Yesterday</p>
                </div>
              </div>
              <div className="flex items-center space-x-3">
                <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center">
                  <span className="text-lg">⭐</span>
                </div>
                <div>
                  <p className="text-sm font-medium text-slate-800">Week Streak</p>
                  <p className="text-xs text-slate-500">3 days ago</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StudentDashboard;