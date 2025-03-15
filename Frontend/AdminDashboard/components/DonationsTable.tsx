"use client";

import { useState } from "react";
import TableHeader from "./TableHeader";
import TableRow from "./TableRow";
import BackgroundImage from "./BackgroundImage";

export default function DonationsTable() {
  const [donations] = useState([
    { id: 1, designation: "Dattes", quantity: "5 Kg", address: "Bab Ezzouar", date: "Il y a 10 min" },
    { id: 2, designation: "Lait", quantity: "2 L", address: "Oued Smar", date: "Il y a 35 min" },
    { id: 3, designation: "Dattes", quantity: "5 Kg", address: "Bab Ezzouar", date: "Il y a 10 min" },
    { id: 4, designation: "Lait", quantity: "2 L", address: "Oued Smar", date: "Il y a 35 min" },
    { id: 5, designation: "Dattes", quantity: "5 Kg", address: "Bab Ezzouar", date: "Il y a 10 min" },
    { id: 6, designation: "Lait", quantity: "2 L", address: "Oued Smar", date: "Il y a 35 min" },
  ]);

  return (
    <div className="relative min-h-screen bg-white flex justify-center items-center p-8">
      <BackgroundImage />
      <div className="bg-white shadow-lg rounded-xl p-6 w-full max-w-4xl relative z-10">
        <h2 className="text-xl font-semibold mb-4 text-black">Dons en temps réel</h2>
        <table className="w-full border-collapse">
          <TableHeader />
          <tbody>
            {donations.map((donation) => (
              <TableRow key={donation.id} donation={donation} />
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
