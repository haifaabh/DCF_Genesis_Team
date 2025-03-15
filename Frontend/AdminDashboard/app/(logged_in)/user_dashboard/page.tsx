// "use client";

// import { BarChart, Utensils, Car, Cake, Flame, Film } from "lucide-react";

// export default function Home() {
//   return (
//     <div className="min-h-screen bg-black flex justify-center items-center p-6">
//     <div className="min-h-screen bg-gray-100 p-6 flex justify-center">
//       <div className="max-w-5xl w-full bg-white shadow-lg rounded-xl p-6 flex gap-6">
//         {/* Left Section */}
//         <div className="flex-1">
//           <h1 className="text-2xl font-semibold">Dons effectués</h1>
//           <p className="text-gray-500 text-sm">01 - 25 March, 2025</p>

//           {/* Bar Chart Placeholder */}
//           <div className="w-full h-20 mt-4 flex items-end gap-1">
//             {Array.from({ length: 20 }, (_, i) => (
//               <div
//                 key={i}
//                 className={`w-3 h-${Math.floor(Math.random() * 10) + 3} bg-blue-300 rounded`}
//               ></div>
//             ))}
//             <div className="w-3 h-16 bg-blue-500 rounded"></div>
//           </div>

//           {/* Transactions List */}
//           <div className="mt-6">
//             <h2 className="text-lg font-semibold">Aujourd'hui</h2>
//             <div className="space-y-4 mt-4">
//               {/* Transaction Item */}
//               {[
//                 { icon: Utensils, color: "text-blue-500", title: "Alimentation générale", amount: "-326.800", place: "Belanja di pasar" },
//                 { icon: Car, color: "text-purple-500", title: "Transportation", amount: "+15.000", place: "Naik bus umum" },
//                 { icon: Cake, color: "text-yellow-500", title: "Desserts ou aliments sucrés", amount: "-185.750", place: "Bayar Lisrik" }
//               ].map((item, index) => (
//                 <div key={index} className="flex items-center gap-4 bg-gray-50 p-4 rounded-lg">
//                   <item.icon className={`w-8 h-8 ${item.color}`} />
//                   <div className="flex-1">
//                     <p className="text-gray-800 font-medium">{item.title}</p>
//                     <p className="text-gray-500 text-sm">{item.place}</p>
//                   </div>
//                   <p className={`font-semibold ${item.amount.startsWith("+") ? "text-green-500" : "text-red-500"}`}>
//                     {item.amount}
//                   </p>
//                 </div>
//               ))}
//             </div>
//           </div>
//         </div>

//         {/* Right Sidebar with Progress Bars */}
//         <div className="w-80 bg-gray-50 p-6 rounded-lg">
//           <h2 className="text-lg font-semibold">Chiffres importants</h2>
//           <div className="mt-4 space-y-4">
//             {[
//               { label: "Taux de redistribution", value: "872.400", percentage: 30 },
//               { label: "Nourriture sauvée", value: "1.378.200", percentage: 60 },
//               { label: "Repas distribués", value: "928.500", percentage: 50 },
//               { label: "Transportation", value: "420.700", percentage: 25 },
//               { label: "Vehicle", value: "520.000", percentage: 40 }
//             ].map((item, index) => (
//               <div key={index}>
//                 <div className="flex justify-between text-sm">
//                   <span className="text-gray-600">{item.label}</span>
//                   <span className="font-semibold">{item.value}</span>
//                 </div>
//                 <div className="w-full h-2 bg-gray-200 rounded-full mt-1">
//                   <div
//                     className="h-2 bg-green-500 rounded-full"
//                     style={{ width: `${item.percentage}%` }}
//                   ></div>
//                 </div>
//               </div>
//             ))}
//           </div>

//           {/* Impact Box */}
//           <div className="bg-orange-100 p-4 mt-6 rounded-lg">
//             <div className="flex items-center gap-2">
//               <BarChart className="w-10 h-10 text-orange-600" />
//               <div>
//                 <h3 className="font-semibold">Augmentez votre impact</h3>
//                 <p className="text-gray-500 text-sm">
//                 Augmentez votre impact
//                 </p>
//               </div>
//             </div>
//           </div>
//         </div>
//       </div>
//     </div>
//     </div>
   
//   );
// }
"use client";

import Sidebar from "@/components/Sidebar";
import BarChartSection from "@/components/BarChartSection";
import StatsSidebar from "@/components/StatsSidebar";

export default function Home() {
  return (
    <div className="min-h-screen flex bg-[#FF4B66]">
      <Sidebar />
      <div className="min-h-screen p-6 flex justify-center bg-[#FF4B66]">
        <div className="max-w-5xl w-full bg-white shadow-lg rounded-xl p-6 flex justify-between">
          <BarChartSection />
          <StatsSidebar />
        </div>
      </div>
    </div>
  );
}
