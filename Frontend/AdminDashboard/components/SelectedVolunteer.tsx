"use client";

interface Volunteer {
  id: number;
  name: string;
  phone: string;
  available: boolean;
}

const SelectedVolunteer = ({ volunteer }: { volunteer: Volunteer | null }) => {
  if (!volunteer) return null;

  return (
    <div className="bg-white p-4 shadow rounded-lg mt-4">
      <h3 className="text-lg font-semibold">{volunteer.name}</h3>
      <p className="text-gray-600">Oued Smar</p>
      <p className="text-gray-600">Contact: {volunteer.phone}</p>
      <p className="text-gray-600">Véhicule: Oui</p>
      <p className="text-gray-600">Capacité: 15 cartons</p>
      <button className="bg-green-500 text-white px-6 py-2 rounded mt-3">Assigner</button>
    </div>
  );
};

export default SelectedVolunteer;
