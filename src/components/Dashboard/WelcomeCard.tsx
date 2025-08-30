import React from 'react';
import { User } from '../../types';

interface WelcomeCardProps {
  user: User | null;
}

const WelcomeCard: React.FC<WelcomeCardProps> = ({ user }) => {
  const getGreeting = () => {
    const hour = new Date().getHours();
    if (hour < 12) return 'Good morning';
    if (hour < 18) return 'Good afternoon';
    return 'Good evening';
  };

  const getEmoji = () => {
    const hour = new Date().getHours();
    if (hour < 12) return '🌅';
    if (hour < 18) return '☀️';
    return '🌙';
  };

  return (
    <div className="bg-gradient-to-r from-blue-500 to-purple-600 rounded-2xl p-6 text-white">
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-2xl font-bold mb-1">
            {getGreeting()}, {user?.name}! {getEmoji()}
          </h2>
          <p className="text-blue-100">
            Ready to continue your learning journey today?
          </p>
        </div>
        <div className="hidden md:block">
          <div className="w-20 h-20 bg-white/20 rounded-full flex items-center justify-center">
            <span className="text-4xl">📚</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default WelcomeCard;