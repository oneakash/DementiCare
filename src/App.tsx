import React, { useState } from 'react';
import { AuthProvider, useAuth } from './context/AuthContext';
import Header from './components/Layout/Header';
import Sidebar from './components/Layout/Sidebar';
import LoginForm from './components/Auth/LoginForm';
import RegisterForm from './components/Auth/RegisterForm';
import StudentDashboard from './components/Dashboard/StudentDashboard';
import CaregiverDashboard from './components/Dashboard/CaregiverDashboard';
import VisualSchedule from './components/Features/VisualSchedule';

const AppContent: React.FC = () => {
  const { user, isLoading } = useAuth();
  const [isLoginMode, setIsLoginMode] = useState(true);
  const [currentPath, setCurrentPath] = useState('/dashboard');

  if (isLoading) {
    return (
      <div className="min-h-screen bg-slate-50 flex items-center justify-center">
        <div className="text-center">
          <div className="w-16 h-16 bg-gradient-to-br from-blue-500 to-purple-600 rounded-2xl flex items-center justify-center mx-auto mb-4 animate-pulse">
            <span className="text-white font-bold text-2xl">DC</span>
          </div>
          <p className="text-slate-600">Loading DementiCare...</p>
        </div>
      </div>
    );
  }

  if (!user) {
    return (
      <div className="min-h-screen bg-slate-50 flex items-center justify-center p-4">
        <div className="w-full max-w-md">
          {isLoginMode ? (
            <LoginForm onToggleMode={() => setIsLoginMode(false)} />
          ) : (
            <RegisterForm onToggleMode={() => setIsLoginMode(true)} />
          )}
        </div>
      </div>
    );
  }

  const renderContent = () => {
    switch (currentPath) {
      case '/dashboard':
        return user.role === 'caregiver' ? <CaregiverDashboard /> : <StudentDashboard />;
      case '/schedule':
        return <VisualSchedule />;
      case '/flashcards':
        return (
          <div className="p-6 text-center">
            <h2 className="text-2xl font-bold text-slate-800 mb-4">Flashcards</h2>
            <p className="text-slate-600">Interactive flashcards coming soon!</p>
          </div>
        );
      case '/games':
        return (
          <div className="p-6 text-center">
            <h2 className="text-2xl font-bold text-slate-800 mb-4">Memory Games</h2>
            <p className="text-slate-600">Fun memory games coming soon!</p>
          </div>
        );
      case '/stories':
        return (
          <div className="p-6 text-center">
            <h2 className="text-2xl font-bold text-slate-800 mb-4">Social Stories</h2>
            <p className="text-slate-600">Interactive social stories coming soon!</p>
          </div>
        );
      case '/achievements':
        return (
          <div className="p-6 text-center">
            <h2 className="text-2xl font-bold text-slate-800 mb-4">Achievements</h2>
            <p className="text-slate-600">Your achievement wall coming soon!</p>
          </div>
        );
      case '/mood':
        return (
          <div className="p-6 text-center">
            <h2 className="text-2xl font-bold text-slate-800 mb-4">Mood Tracker</h2>
            <p className="text-slate-600">Mood tracking tools coming soon!</p>
          </div>
        );
      case '/reports':
        return (
          <div className="p-6 text-center">
            <h2 className="text-2xl font-bold text-slate-800 mb-4">Progress Reports</h2>
            <p className="text-slate-600">Detailed progress analytics coming soon!</p>
          </div>
        );
      case '/community':
        return (
          <div className="p-6 text-center">
            <h2 className="text-2xl font-bold text-slate-800 mb-4">Community</h2>
            <p className="text-slate-600">Community features coming soon!</p>
          </div>
        );
      default:
        return user.role === 'caregiver' ? <CaregiverDashboard /> : <StudentDashboard />;
    }
  };

  return (
    <div className="min-h-screen bg-slate-50">
      <Header />
      <div className="flex">
        <Sidebar currentPath={currentPath} onNavigate={setCurrentPath} />
        <main className="flex-1">
          {renderContent()}
        </main>
      </div>
    </div>
  );
};

function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}

export default App;