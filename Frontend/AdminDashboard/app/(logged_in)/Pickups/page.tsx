"use client";

import { useState } from "react";
import { FaPhone, FaCar } from "react-icons/fa";
import { IoIosArrowDown, IoIosArrowUp } from "react-icons/io";
import Map from "@/components/Map";

interface Volunteer {
  id: number;
  name: string;
  phone: string;
  address: string;
  vehicle: boolean;
  capacity: number;
}

const volunteers: Volunteer[] = [
  { id: 1, name: "MALLEK Dina", phone: "0777777777", address: "Oued Smar", vehicle: true, capacity: 15 },
  { id: 2, name: "BOUHADI Haifaa", phone: "0666666666", address: "Bab Ezzouar", vehicle: false, capacity: 10 },
  { id: 3, name: "BOUYAHIAOUI Meriem", phone: "0555555555", address: "El Harrach", vehicle: true, capacity: 20 },
  { id: 4, name: "DJABRI Maroua", phone: "0771234567", address: "Kouba", vehicle: false, capacity: 8 },
  { id: 5, name: "XYZ", phone: "0612345678", address: "Alger Centre", vehicle: true, capacity: 12 },
];

export default function Pickups() {
  const [selectedVolunteer, setSelectedVolunteer] = useState<Volunteer | null>(null);
  const [isExpanded, setIsExpanded] = useState(false);

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <div className="max-w-4xl mx-auto">
        <div className="bg-white p-6 shadow rounded-lg mb-6 relative">
          <h2 className="text-lg font-bold">Ramassages en attente</h2>
          <button className="absolute top-4 right-4 bg-yellow-500 text-white px-4 py-2 rounded">
            Voir tout
          </button>
          <Map />
        </div>

        <div className="bg-white p-6 shadow rounded-lg mb-6 relative">
          <h2 className="text-lg font-bold mb-2">Bénévoles disponibles à proximité</h2>
          <button className="absolute top-6 right-6 bg-red-500 text-white px-4 py-2 rounded">
            Rafraîchir 🔄
          </button>
          <ul className="mt-4 space-y-2">
            {volunteers.map((volunteer) => (
              <li
                key={volunteer.id}
                className="cursor-pointer p-3 border rounded-lg hover:bg-gray-100"
                onClick={() => {
                  setSelectedVolunteer(volunteer);
                  setIsExpanded(true);
                }}
              >
                <strong>{volunteer.name}</strong>
              </li>
            ))}
          </ul>
        </div>

        {selectedVolunteer && (
          <div className="bg-white p-6 shadow rounded-lg mb-6">
            <div className="flex justify-between items-center cursor-pointer" onClick={() => setIsExpanded(!isExpanded)}>
              <h2 className="text-lg font-bold">{selectedVolunteer.name}</h2>
              {isExpanded ? <IoIosArrowUp /> : <IoIosArrowDown />}
            </div>

            {isExpanded && (
              <div className="mt-4 border-t pt-4">
                <p className="text-gray-700">{selectedVolunteer.address}</p>
                <p className="flex items-center gap-2"><FaPhone /> {selectedVolunteer.phone}</p>
                <p className="flex items-center gap-2">
                  <FaCar /> Véhicule : {selectedVolunteer.vehicle ? "Oui" : "Non"}
                </p>
                <p>Capacité : {selectedVolunteer.capacity} cartons</p>
                <button className="bg-green-500 text-white px-4 py-2 rounded mt-4">Assigner</button>
              </div>
            )}
          </div>
        )}
      </div>
    </div>
  );
}
