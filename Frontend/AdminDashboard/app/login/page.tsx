
"use client"; 
import { useState } from "react";
import Image from "next/image";

export default function Login() {
  const [mobile, setMobile] = useState("");

  const handleGetOTP = () => {
    alert(`OTP sent to: ${mobile}`);
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-white relative">
      {/* Background Triangle (Pointing Right) */}
     {/* Background Image (Instead of Triangle) */}
     <Image 
  src="/Polygon1.png" 
  alt="Decorative Shape" 
  width={500} 
  height={600} 
  className="absolute left-0 top-0 z-0"
/>

      {/* Red Dots on the Right */}
      <div className="absolute top-5 right-5 grid grid-cols-3 gap-1">
        {[...Array(6)].map((_, i) => (
          <div key={i} className="w-3 h-3 bg-red-500 rounded-full"></div>
        ))}
      </div>

      {/* Login Card */}
      <div className="bg-white shadow-lg rounded-xl p-8 w-96 z-10 mt-2">
        <h2 className="text-2xl font-semibold text-center">Login</h2>
        <p className="text-gray-600 text-center mt-2">
          An OTP will be sent to your mobile number for verification
        </p>

        <input
          type="text"
          className="w-full mt-4 p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-red-500"
          placeholder="Enter your mobile number"
          value={mobile}
          onChange={(e) => setMobile(e.target.value)}
        />

        <p className="text-xs text-gray-400 mt-2">
          *Lorem Ipsum is simply dummy text of the printing industry
        </p>

        <button
          onClick={handleGetOTP}
          className="w-full mt-4 bg-red-500 text-white py-3 rounded-lg font-semibold hover:bg-red-600 transition"
        >
          Get OTP
        </button>
      </div>

      {/* Bottom Right Image */}
      <Image 
  src="/grocery-box.png"
  alt="Decorative Shape" 
  width={200} 
  height={200} 
  className="absolute   right-0 mt-80"
/>
    </div>
  );
}
