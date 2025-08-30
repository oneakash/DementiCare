import React from 'react';
import { Task } from '../../types';
import { CheckCircleIcon, ClockIcon, PlayIcon } from '@heroicons/react/24/outline';
import { CheckCircleIcon as CheckCircleSolid } from '@heroicons/react/24/solid';
import { clsx } from 'clsx';

interface TaskCardProps {
  task: Task;
  onStatusChange: (taskId: string, status: Task['status']) => void;
}

const TaskCard: React.FC<TaskCardProps> = ({ task, onStatusChange }) => {
  const getStatusIcon = () => {
    switch (task.status) {
      case 'completed':
        return <CheckCircleSolid className="w-6 h-6 text-green-500" />;
      case 'in_progress':
        return <PlayIcon className="w-6 h-6 text-blue-500" />;
      default:
        return <ClockIcon className="w-6 h-6 text-slate-400" />;
    }
  };

  const getStatusColor = () => {
    switch (task.status) {
      case 'completed':
        return 'border-green-200 bg-green-50';
      case 'in_progress':
        return 'border-blue-200 bg-blue-50';
      default:
        return 'border-slate-200 bg-white';
    }
  };

  const handleToggleComplete = () => {
    const newStatus = task.status === 'completed' ? 'pending' : 'completed';
    onStatusChange(task.id, newStatus);
  };

  return (
    <div className={clsx(
      'border rounded-xl p-4 transition-all duration-200 hover:shadow-md',
      getStatusColor()
    )}>
      <div className="flex items-start justify-between">
        <div className="flex-1">
          <div className="flex items-center space-x-3 mb-2">
            {getStatusIcon()}
            <h4 className={clsx(
              'font-semibold',
              task.status === 'completed' ? 'text-green-800' : 'text-slate-800'
            )}>
              {task.title}
            </h4>
          </div>
          
          {task.description && (
            <p className="text-sm text-slate-600 mb-3">{task.description}</p>
          )}
          
          <div className="flex items-center space-x-4 text-sm text-slate-500">
            {task.scheduledTime && (
              <span className="flex items-center space-x-1">
                <ClockIcon className="w-4 h-4" />
                <span>{task.scheduledTime}</span>
              </span>
            )}
            {task.duration && (
              <span>{task.duration} min</span>
            )}
          </div>
          
          {task.visualAids.length > 0 && (
            <div className="flex space-x-2 mt-3">
              {task.visualAids.map((emoji, index) => (
                <span key={index} className="text-2xl">{emoji}</span>
              ))}
            </div>
          )}
        </div>
        
        <button
          onClick={handleToggleComplete}
          className={clsx(
            'ml-4 p-2 rounded-full transition-colors',
            task.status === 'completed'
              ? 'text-green-600 hover:bg-green-100'
              : 'text-slate-400 hover:bg-slate-100 hover:text-slate-600'
          )}
        >
          <CheckCircleIcon className="w-8 h-8" />
        </button>
      </div>
    </div>
  );
};

export default TaskCard;