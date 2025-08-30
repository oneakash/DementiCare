import React from 'react';
import { useAuth } from '../../context/AuthContext';
import { useTasks } from '../../hooks/useTasks';
import {
  UsersIcon,
  ChartBarIcon,
  PlusIcon,
  BellIcon,
} from '@heroicons/react/24/outline';
import WelcomeCard from './WelcomeCard';

const CaregiverDashboard: React.FC = () => {
  const { user } = useAuth();
  const { tasks } = useTasks();

  const students = [
    { id: '1', name: 'Alex Johnson', progress: 85, recentActivity: '2 hours ago' },
    { id: '2', name: 'Sam Wilson', progress: 72, recentActivity: '4 hours ago' },
    { id: '3', name: 'Jordan Smith', progress: 93, recentActivity: '1 hour ago' },
  ];

  const recentActivities = [
    { student: 'Alex Johnson', activity: 'Completed Visual Schedule', time: '10 minutes ago' },
    { student: 'Sam Wilson', activity: 'Practiced Flashcards', time: '30 minutes ago' },
    { student: 'Jordan Smith', activity: 'Finished Social Story', time: '1 hour ago' },
  ];

  return (
    <div className="p-6 space-y-6">
      <WelcomeCard user={user} />
      
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        <div className="lg:col-span-2 space-y-6">
          <div className="bg-white rounded-xl border border-slate-200 p-6">
            <div className="flex items-center justify-between mb-4">
              <h3 className="text-xl font-semibold text-slate-800">My Students</h3>
              <button className="flex items-center space-x-2 bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition-colors">
                <PlusIcon className="w-4 h-4" />
                <span>Add Student</span>
              </button>
            </div>
            
            <div className="space-y-4">
              {students.map((student) => (
                <div key={student.id} className="border border-slate-200 rounded-lg p-4 hover:bg-slate-50 transition-colors">
                  <div className="flex items-center justify-between mb-2">
                    <h4 className="font-medium text-slate-800">{student.name}</h4>
                    <span className="text-sm text-slate-500">{student.recentActivity}</span>
                  </div>
                  <div className="flex items-center space-x-3">
                    <div className="flex-1">
                      <div className="bg-slate-200 rounded-full h-2">
                        <div 
                          className="bg-green-500 rounded-full h-2 transition-all duration-500"
                          style={{ width: `${student.progress}%` }}
                        />
                      </div>
                    </div>
                    <span className="text-sm font-medium text-slate-700">{student.progress}%</span>
                  </div>
                </div>
              ))}
            </div>
          </div>

          <div className="bg-white rounded-xl border border-slate-200 p-6">
            <h3 className="text-xl font-semibold text-slate-800 mb-4">Recent Activities</h3>
            <div className="space-y-3">
              {recentActivities.map((activity, index) => (
                <div key={index} className="flex items-start space-x-3 p-3 bg-slate-50 rounded-lg">
                  <div className="w-8 h-8 bg-blue-100 rounded-full flex items-center justify-center flex-shrink-0">
                    <UsersIcon className="w-4 h-4 text-blue-600" />
                  </div>
                  <div className="flex-1">
                    <p className="text-sm font-medium text-slate-800">
                      <span className="text-blue-600">{activity.student}</span> {activity.activity}
                    </p>
                    <p className="text-xs text-slate-500">{activity.time}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>

        <div className="space-y-6">
          <div className="bg-white rounded-xl border border-slate-200 p-6">
            <h4 className="font-semibold text-slate-800 mb-4">Quick Stats</h4>
            <div className="space-y-4">
              <div className="flex items-center justify-between">
                <span className="text-slate-600">Total Students</span>
                <span className="font-bold text-slate-800">3</span>
              </div>
              <div className="flex items-center justify-between">
                <span className="text-slate-600">Active Sessions</span>
                <span className="font-bold text-slate-800">2</span>
              </div>
              <div className="flex items-center justify-between">
                <span className="text-slate-600">Completed Tasks</span>
                <span className="font-bold text-slate-800">12</span>
              </div>
              <div className="flex items-center justify-between">
                <span className="text-slate-600">Avg. Progress</span>
                <span className="font-bold text-slate-800">83%</span>
              </div>
            </div>
          </div>

          <div className="bg-gradient-to-br from-green-500 to-blue-500 rounded-xl p-6 text-white">
            <h4 className="font-semibold mb-2">🎯 Today's Goal</h4>
            <p className="text-green-100 text-sm mb-4">
              Monitor all students' progress and provide support
            </p>
            <div className="bg-white/20 rounded-full h-2">
              <div className="bg-white rounded-full h-2 w-3/4" />
            </div>
            <p className="text-xs text-green-100 mt-2">3/4 students checked</p>
          </div>

          <div className="bg-white rounded-xl border border-slate-200 p-6">
            <h4 className="font-semibold text-slate-800 mb-3 flex items-center">
              <BellIcon className="w-5 h-5 mr-2" />
              Notifications
            </h4>
            <div className="space-y-3">
              <div className="p-3 bg-yellow-50 border border-yellow-200 rounded-lg">
                <p className="text-sm font-medium text-yellow-800">Sam needs help</p>
                <p className="text-xs text-yellow-600">Struggling with flashcards</p>
              </div>
              <div className="p-3 bg-blue-50 border border-blue-200 rounded-lg">
                <p className="text-sm font-medium text-blue-800">New achievement unlocked</p>
                <p className="text-xs text-blue-600">Jordan completed week streak</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CaregiverDashboard;