"use client";

export default function Sidebar() {
  return (
    <div className="w-1/3 h-screen bg-[#FF4B66] text-white flex flex-col items-center py-6">
      {/* Profile Section */}
      <div className="relative">
        <img
          src="https://randomuser.me/api/portraits/women/44.jpg"
          alt="Profile"
          className="w-16 h-16 rounded-full object-cover border-2 border-white"
        />
        <div className="absolute top-0 right-0 bg-[#FF4B66] w-5 h-5 rounded-full flex items-center justify-center text-xs font-bold">
          4
        </div>
      </div>
      <h2 className="mt-3 text-lg font-bold">Ahmed</h2>
      <p className="text-sm text-gray-200">ahmed@email.com</p>

      {/* Navigation Menu */}
      <nav className="mt-8 space-y-4 text-sm w-full text-center">
        <a href="#" className="block font-semibold">Accueil</a>
        <a href="#" className="block text-gray-300 hover:text-white">Suivi des dons</a>
        <a href="#" className="block text-gray-300 hover:text-white">Ramassages</a>
        <a href="#" className="block text-gray-300 hover:text-white">A propos</a>
        <a href="#" className="block text-gray-300 hover:text-white">Leaderboard</a>
        <a href="#" className="block text-gray-300 hover:text-white">Paramètres</a>
      </nav>
    </div>
  );
}
