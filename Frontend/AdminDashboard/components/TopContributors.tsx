'use client';

import { Medal } from "lucide-react";

export default function TopContributors() {
  return (
    <div className="max-w-5xl w-full bg-white shadow-lg rounded-xl p-6">
      {/* Top Contributors Section */}
      <div className="flex justify-center gap-4 mb-6">
        <div className="flex flex-col items-center bg-gray-100 rounded-lg p-4 shadow-md w-32">
          <Medal className="text-gray-500" />
          <span className="font-bold">ghr678</span>
        </div>
        <div className="flex flex-col items-center bg-yellow-100 rounded-lg p-4 shadow-md w-32">
          <Medal className="text-yellow-500" />
          <span className="font-bold">sneha1809</span>
        </div>
        <div className="flex flex-col items-center bg-gray-200 rounded-lg p-4 shadow-md w-32">
          <Medal className="text-yellow-500" />
          <span className="font-bold">br7609</span>
        </div>
      </div>

      {/* Leaderboard Table */}
      <table className="w-full text-left border-collapse">
        <thead>
          <tr className="bg-gray-100">
            <th className="p-3 text-gray-700">Username</th>
            <th className="p-3 text-gray-700">Rang</th>
            <th className="p-3 text-gray-700">Dons cumulés</th>
          </tr>
        </thead>
        <tbody>
          {[
            { username: "@sn809", rank: 4, donations: 400 },
            { username: "@ayush123", rank: 5, donations: 367 },
            { username: "@ruchi4567", rank: 6, donations: 340 },
            { username: "@frenny56789", rank: 7, donations: 320 },
            { username: "@vijay678", rank: 8, donations: 318 },
            { username: "@brinda670988", rank: 9, donations: 310 },
          ].map((user, index) => (
            <tr key={index} className="border-b hover:bg-gray-50">
              <td className="p-3">{user.username}</td>
              <td className="p-3">{user.rank}</td>
              <td className="p-3">{user.donations}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
