import React from 'react';
import { useAuth } from '../../context/AuthContext';
import {
  HomeIcon,
  CalendarDaysIcon,
  BookOpenIcon,
  PuzzlePieceIcon,
  ChatBubbleBottomCenterTextIcon,
  TrophyIcon,
  HeartIcon,
  ChartBarIcon,
  UsersIcon,
} from '@heroicons/react/24/outline';
import { clsx } from 'clsx';

interface NavItem {
  name: string;
  href: string;
  icon: React.ComponentType<{ className?: string }>;
  roles: string[];
}

const navigation: NavItem[] = [
  { name: 'Dashboard', href: '/dashboard', icon: HomeIcon, roles: ['student', 'caregiver', 'admin'] },
  { name: 'Visual Schedule', href: '/schedule', icon: CalendarDaysIcon, roles: ['student', 'caregiver'] },
  { name: 'Flashcards', href: '/flashcards', icon: BookOpenIcon, roles: ['student', 'caregiver'] },
  { name: 'Memory Games', href: '/games', icon: PuzzlePieceIcon, roles: ['student'] },
  { name: 'Social Stories', href: '/stories', icon: ChatBubbleBottomCenterTextIcon, roles: ['student', 'caregiver'] },
  { name: 'Achievements', href: '/achievements', icon: TrophyIcon, roles: ['student'] },
  { name: 'Mood Tracker', href: '/mood', icon: HeartIcon, roles: ['student', 'caregiver'] },
  { name: 'Progress Reports', href: '/reports', icon: ChartBarIcon, roles: ['caregiver', 'admin'] },
  { name: 'Community', href: '/community', icon: UsersIcon, roles: ['student', 'caregiver'] },
];

interface SidebarProps {
  currentPath: string;
  onNavigate: (path: string) => void;
}

const Sidebar: React.FC<SidebarProps> = ({ currentPath, onNavigate }) => {
  const { user } = useAuth();

  const filteredNavigation = navigation.filter(item => 
    user && item.roles.includes(user.role)
  );

  return (
    <aside className="w-64 bg-white border-r border-slate-200 min-h-screen">
      <nav className="p-4 space-y-2">
        {filteredNavigation.map((item) => {
          const isActive = currentPath === item.href;
          return (
            <button
              key={item.name}
              onClick={() => onNavigate(item.href)}
              className={clsx(
                'w-full flex items-center space-x-3 px-4 py-3 text-left rounded-xl transition-all duration-200',
                isActive
                  ? 'bg-gradient-to-r from-blue-500 to-purple-600 text-white shadow-lg'
                  : 'text-slate-600 hover:bg-slate-100 hover:text-slate-800'
              )}
            >
              <item.icon className="w-5 h-5 flex-shrink-0" />
              <span className="font-medium">{item.name}</span>
            </button>
          );
        })}
      </nav>
    </aside>
  );
};

export default Sidebar;