"use client";

import Image from "next/image";
import { useState } from "react";

export default function Register() {
  const [formData, setFormData] = useState({
    organizationName: "",
    address: "",
    responsiblePerson: "",
    phoneNumber: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    console.log("Form Submitted:", formData);
  };

  return (
    <div className="relative min-h-screen bg-gray-100 flex items-center justify-center p-6">
      {/* Background Triangle */}
      <div className="absolute top-0 left-0 w-64 h-64">
        <Image src="/Polygon3.png" alt="Background Triangle" width={250} height={250} />
      </div>

      {/* Background Dots */}
      <div className="absolute top-6 right-6 w-20">
        <Image src="/dots.png" alt="Decorative Dots" width={100} height={50} />
      </div>

      <div className="bg-white shadow-xl rounded-lg p-8 max-w-lg   relative z-10 w-2/3">
        <h2 className="text-2xl font-semibold mb-6 text-[#DADADA]">Créer un compte</h2>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-gray-600">Nom de l’organisation</label>
            <input
              type="text"
              name="organizationName"
              value={formData.organizationName}
              onChange={handleChange}
              placeholder="Name"
              className="w-full p-2 border border-gray-300 rounded"
            />
          </div>

          <div>
            <label className="block text-gray-600">Adresse</label>
            <textarea
              name="address"
              value={formData.address}
              onChange={handleChange}
              placeholder="Adresse complète"
              className="w-full p-2 border border-gray-300 rounded"
            />
          </div>

          <div>
            <label className="block text-gray-600">Responsable de l’organisation</label>
            <input
              type="text"
              name="responsiblePerson"
              value={formData.responsiblePerson}
              onChange={handleChange}
              placeholder="Name"
              className="w-full p-2 border border-gray-300 rounded"
            />
          </div>

          <div>
            <label className="block text-gray-600">Phone Number</label>
            <input
              type="text"
              name="phoneNumber"
              value={formData.phoneNumber}
              onChange={handleChange}
              placeholder="Number"
              className="w-full p-2 border border-gray-300 rounded"
            />
          </div>

          <button type="submit" className="bg-white text-black   rounded-md flex items-center justify-center gap-2">
            Submit
            <span className="rotate-90">🔄</span>
          </button>
        </form>
      </div>

      {/* Donation Box Image */}
      <div className="absolute bottom-4 right-6 w-40">
        <Image src="/132622022672068924_1.jpg_Avatar-removebg-preview 1.png" alt="Donation Box" width={150} height={100} />
      </div>
    </div>
  );
}
