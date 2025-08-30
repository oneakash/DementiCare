import React from 'react';

interface ProgressCardProps {
  title: string;
  progress: number;
  subtitle: string;
}

const ProgressCard: React.FC<ProgressCardProps> = ({ title, progress, subtitle }) => {
  return (
    <div className="bg-white rounded-xl border border-slate-200 p-6">
      <h4 className="font-semibold text-slate-800 mb-2">{title}</h4>
      <div className="mb-4">
        <div className="flex justify-between items-center mb-2">
          <span className="text-2xl font-bold text-slate-800">{Math.round(progress)}%</span>
          <span className="text-sm text-slate-500">{subtitle}</span>
        </div>
        <div className="bg-slate-200 rounded-full h-3">
          <div 
            className="bg-gradient-to-r from-blue-500 to-purple-600 rounded-full h-3 transition-all duration-500"
            style={{ width: `${progress}%` }}
          />
        </div>
      </div>
    </div>
  );
};

export default ProgressCard;