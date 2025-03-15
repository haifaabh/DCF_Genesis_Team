"use client";

interface Volunteer {
  id: number;
  name: string;
  phone: string;
  available: boolean;
}

const volunteers: Volunteer[] = [
  { id: 1, name: "MALLEK Dina", phone: "XXXXXXXXX", available: true },
  { id: 2, name: "BOUJAR Hafsa", phone: "XXXXXXXXX", available: true },
  { id: 3, name: "DJABLIKHOUA Meriem", phone: "XXXXXXXXX", available: false },
];

const VolunteersList = ({ onSelect }: { onSelect: (volunteer: Volunteer) => void }) => {
  return (
    <div className="bg-white p-4 shadow rounded-lg">
      <h3 className="text-lg font-semibold mb-2">Bénévoles disponibles à proximité</h3>
      <ul className="space-y-2">
        {volunteers.map((volunteer) => (
          <li key={volunteer.id} className="flex justify-between items-center p-2 border rounded">
            <span>{volunteer.name}</span>
            <button
              className={`px-4 py-2 text-white rounded ${
                volunteer.available ? "bg-pink-500" : "bg-gray-400 cursor-not-allowed"
              }`}
              onClick={() => volunteer.available && onSelect(volunteer)}
              disabled={!volunteer.available}
            >
              {volunteer.available ? "Sélectionner" : "Indispo"}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default VolunteersList;
