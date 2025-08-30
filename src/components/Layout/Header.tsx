import React from 'react';
import { useAuth } from '../../context/AuthContext';
import { UserCircleIcon, BellIcon, Cog6ToothIcon } from '@heroicons/react/24/outline';

const Header: React.FC = () => {
  const { user, logout } = useAuth();

  return (
    <header className="bg-white border-b border-slate-200 px-4 py-3">
      <div className="max-w-7xl mx-auto flex items-center justify-between">
        <div className="flex items-center space-x-3">
          <div className="w-10 h-10 bg-gradient-to-br from-blue-500 to-purple-600 rounded-xl flex items-center justify-center">
            <span className="text-white font-bold text-lg">DC</span>
          </div>
          <div>
            <h1 className="text-xl font-bold text-slate-800">DementiCare</h1>
            <p className="text-sm text-slate-500">Learning made simple</p>
          </div>
        </div>

        {user && (
          <div className="flex items-center space-x-4">
            <button className="p-2 text-slate-600 hover:text-slate-800 hover:bg-slate-100 rounded-lg transition-colors">
              <BellIcon className="w-5 h-5" />
            </button>
            <button className="p-2 text-slate-600 hover:text-slate-800 hover:bg-slate-100 rounded-lg transition-colors">
              <Cog6ToothIcon className="w-5 h-5" />
            </button>
            <div className="flex items-center space-x-3">
              <div className="text-right">
                <p className="text-sm font-medium text-slate-800">{user.name}</p>
                <p className="text-xs text-slate-500 capitalize">{user.role}</p>
              </div>
              <button
                onClick={logout}
                className="flex items-center space-x-2 p-2 hover:bg-slate-100 rounded-lg transition-colors"
              >
                <UserCircleIcon className="w-8 h-8 text-slate-600" />
              </button>
            </div>
          </div>
        )}
      </div>
    </header>
  );
};

export default Header;