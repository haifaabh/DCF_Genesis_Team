'use client';
import { useState } from "react";

export default function FoodAllocation() {
  const [expanded, setExpanded] = useState<number | null>(null);
  
  const items = [
    { id: 1, title: "Dattes 15 Kg", details: "Dattes", quantity: 12 },
    { id: 2, title: "Dattes 10 Kg", details: "Dattes", quantity: null },
    { id: 3, title: "2 boites de lait", details: "Lait", quantity: null },
    { id: 4, title: "1 paquet de lentilles", details: "Lentilles", quantity: null },
  ];

  return (
    <div className="max-w-2xl w-2/3 mx-auto p-4">
      <h1 className="text-lg font-bold">Affectation des denrées alimentaires</h1>
      <p className="text-sm text-gray-500">Aidez ceux qui en ont besoin en répondant aux demandes actives</p>
      <div className="mt-4 space-y-4">
        {items.map((item) => (
          <div 
            key={item.id} 
            className="border rounded-lg p-4 shadow-sm bg-white relative"
          >
            <h2 className="font-bold">{item.title}</h2>
            {expanded === item.id && (
              <div className="border rounded-md p-3 mt-2 bg-gray-100">
                <p>{item.details}</p>
                <p className="text-sm text-gray-500">ABC Hospital Building, XYZ Road, Place, District</p>
                <p className="text-sm text-gray-500">Contact Number : XXXXXXXXXX</p>
                <input
                  type="number"
                  defaultValue={item.quantity || 1}
                  className="mt-2 w-full border rounded-md p-2"
                />
              </div>
            )}
            <button 
              className="absolute top-4 right-4 bg-yellow-400 px-3 py-1 text-sm rounded"
              onClick={() => setExpanded(expanded === item.id ? null : item.id)}
            >
              Affecter
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
