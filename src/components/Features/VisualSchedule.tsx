import React, { useState } from 'react';
import { CalendarDaysIcon, ClockIcon, PlusIcon } from '@heroicons/react/24/outline';
import { format } from 'date-fns';

interface ScheduleItem {
  id: string;
  time: string;
  title: string;
  description: string;
  emoji: string;
  completed: boolean;
}

const VisualSchedule: React.FC = () => {
  const [scheduleItems, setScheduleItems] = useState<ScheduleItem[]>([
    {
      id: '1',
      time: '08:00',
      title: 'Morning Routine',
      description: 'Brush teeth, wash face, get dressed',
      emoji: '🌅',
      completed: true,
    },
    {
      id: '2',
      time: '09:00',
      title: 'Breakfast',
      description: 'Healthy breakfast with family',
      emoji: '🍳',
      completed: true,
    },
    {
      id: '3',
      time: '10:00',
      title: 'Learning Time',
      description: 'Flashcards and reading practice',
      emoji: '📚',
      completed: false,
    },
    {
      id: '4',
      time: '11:30',
      title: 'Sensory Break',
      description: 'Quiet time with calming music',
      emoji: '🎵',
      completed: false,
    },
    {
      id: '5',
      time: '12:00',
      title: 'Lunch',
      description: 'Nutritious meal and social time',
      emoji: '🥪',
      completed: false,
    },
    {
      id: '6',
      time: '14:00',
      title: 'Social Story Practice',
      description: 'Practice social scenarios',
      emoji: '👥',
      completed: false,
    },
    {
      id: '7',
      time: '15:30',
      title: 'Creative Time',
      description: 'Art, music, or creative activities',
      emoji: '🎨',
      completed: false,
    },
    {
      id: '8',
      time: '17:00',
      title: 'Free Play',
      description: 'Unstructured play time',
      emoji: '⚽',
      completed: false,
    },
  ]);

  const toggleCompletion = (id: string) => {
    setScheduleItems(prev => prev.map(item =>
      item.id === id ? { ...item, completed: !item.completed } : item
    ));
  };

  const currentTime = new Date();
  const currentHour = currentTime.getHours();
  const currentMinute = currentTime.getMinutes();

  const getCurrentTimeString = () => {
    return `${currentHour.toString().padStart(2, '0')}:${currentMinute.toString().padStart(2, '0')}`;
  };

  const isCurrentActivity = (time: string) => {
    const [hour, minute] = time.split(':').map(Number);
    const activityTime = hour * 60 + minute;
    const currentTimeMinutes = currentHour * 60 + currentMinute;
    
    return Math.abs(activityTime - currentTimeMinutes) <= 30;
  };

  return (
    <div className="p-6 max-w-4xl mx-auto">
      <div className="bg-white rounded-2xl border border-slate-200 overflow-hidden">
        <div className="bg-gradient-to-r from-blue-500 to-purple-600 text-white p-6">
          <div className="flex items-center justify-between">
            <div>
              <h1 className="text-2xl font-bold mb-2">Visual Schedule</h1>
              <p className="text-blue-100">Today - {format(new Date(), 'EEEE, MMMM d')}</p>
            </div>
            <div className="text-right">
              <div className="text-3xl font-mono">{getCurrentTimeString()}</div>
              <div className="text-blue-100 text-sm">Current Time</div>
            </div>
          </div>
        </div>

        <div className="p-6">
          <div className="grid gap-4">
            {scheduleItems.map((item, index) => (
              <div
                key={item.id}
                className={`border-2 rounded-xl p-4 transition-all duration-200 ${
                  item.completed
                    ? 'border-green-200 bg-green-50'
                    : isCurrentActivity(item.time)
                    ? 'border-blue-300 bg-blue-50 shadow-lg scale-105'
                    : 'border-slate-200 bg-white hover:border-slate-300'
                }`}
              >
                <div className="flex items-center space-x-4">
                  <button
                    onClick={() => toggleCompletion(item.id)}
                    className={`w-6 h-6 rounded-full border-2 flex items-center justify-center transition-colors ${
                      item.completed
                        ? 'border-green-500 bg-green-500'
                        : 'border-slate-300 hover:border-slate-400'
                    }`}
                  >
                    {item.completed && <span className="text-white text-sm">✓</span>}
                  </button>

                  <div className="text-4xl">{item.emoji}</div>

                  <div className="flex-1">
                    <div className="flex items-center space-x-2 mb-1">
                      <ClockIcon className="w-4 h-4 text-slate-500" />
                      <span className="font-mono text-sm text-slate-600">{item.time}</span>
                      {isCurrentActivity(item.time) && (
                        <span className="bg-blue-500 text-white text-xs px-2 py-1 rounded-full">
                          Now
                        </span>
                      )}
                    </div>
                    <h3 className={`font-semibold text-lg ${
                      item.completed ? 'text-green-800 line-through' : 'text-slate-800'
                    }`}>
                      {item.title}
                    </h3>
                    <p className="text-slate-600 text-sm">{item.description}</p>
                  </div>

                  <div className="text-right">
                    <div className={`text-2xl ${
                      item.completed ? 'text-green-500' : 'text-slate-300'
                    }`}>
                      {item.completed ? '✅' : '⏳'}
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>

          <div className="mt-6 p-4 bg-slate-50 rounded-xl">
            <div className="flex items-center justify-between">
              <div>
                <h3 className="font-semibold text-slate-800">Daily Progress</h3>
                <p className="text-sm text-slate-600">
                  {scheduleItems.filter(item => item.completed).length} of {scheduleItems.length} activities completed
                </p>
              </div>
              <div className="text-right">
                <div className="text-2xl font-bold text-slate-800">
                  {Math.round((scheduleItems.filter(item => item.completed).length / scheduleItems.length) * 100)}%
                </div>
                <div className="w-24 bg-slate-200 rounded-full h-2">
                  <div 
                    className="bg-gradient-to-r from-blue-500 to-green-500 rounded-full h-2 transition-all duration-500"
                    style={{ 
                      width: `${(scheduleItems.filter(item => item.completed).length / scheduleItems.length) * 100}%` 
                    }}
                  />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default VisualSchedule;