"use client";

import { useEffect, useState } from "react";
import TransactionItem from "./TransactionItem";
import { Utensils, Car, Cake } from "lucide-react";

export default function BarChartSection() {
  const [barHeights, setBarHeights] = useState<number[]>([]);

  useEffect(() => {
    setBarHeights(Array.from({ length: 20 }, () => Math.floor(Math.random() * 10) + 3));
  }, []);

  return (
    <div className="flex-1">
      <h1 className="text-2xl font-semibold">Dons effectués</h1>
      <p className="text-gray-500 text-sm">01 - 25 March, 2025</p>

      {/* Bar Chart */}
      <div className="w-full h-20 mt-4 flex items-end gap-1">
        {barHeights.map((height, i) => (
          <div key={i} className={`w-3 h-${height} bg-blue-300 rounded`}></div>
        ))}
        <div className="w-3 h-16 bg-blue-500 rounded"></div>
      </div>

      {/* Transactions List */}
      <div className="mt-6">
        <h2 className="text-lg font-semibold">Aujourd'hui</h2>
        <div className="space-y-4 mt-4">
          {[
            { icon: Utensils, color: "text-blue-500", title: "Alimentation générale", amount: "-326.800", place: "Belanja di pasar" },
            { icon: Car, color: "text-purple-500", title: "Transportation", amount: "+15.000", place: "Naik bus umum" },
            { icon: Cake, color: "text-yellow-500", title: "Desserts ou aliments sucrés", amount: "-185.750", place: "Bayar Lisrik" },
          ].map((item, index) => (
            <TransactionItem key={index} {...item} />
          ))}
        </div>
      </div>
    </div>
  );
}
