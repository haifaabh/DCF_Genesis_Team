import { BarChart } from "lucide-react";

export default function StatsSidebar() {
  return (
    <div className="w-80 bg-gray-50 p-6 rounded-lg flex-shrink-0">
      <h2 className="text-lg font-semibold">Chiffres importants</h2>
      <div className="mt-4 space-y-4">
        {[
          { label: "Taux de redistribution", value: "872.400", percentage: 30 },
          { label: "Nourriture sauvée", value: "1.378.200", percentage: 60 },
          { label: "Repas distribués", value: "928.500", percentage: 50 },
          { label: "Transportation", value: "420.700", percentage: 25 },
          { label: "Vehicle", value: "520.000", percentage: 40 },
        ].map((item, index) => (
          <div key={index}>
            <div className="flex justify-between text-sm">
              <span className="text-gray-600">{item.label}</span>
              <span className="font-semibold">{item.value}</span>
            </div>
            <div className="w-full h-2 bg-gray-200 rounded-full mt-1">
              <div className="h-2 bg-green-500 rounded-full" style={{ width: `${item.percentage}%` }}></div>
            </div>
          </div>
        ))}
      </div>

      {/* Impact Box */}
      <div className="bg-orange-100 p-4 mt-6 rounded-lg">
        <div className="flex items-center gap-2">
          <BarChart className="w-10 h-10 text-orange-600" />
          <div>
            <h3 className="font-semibold">Augmentez votre impact</h3>
            <p className="text-gray-500 text-sm">Augmentez votre impact</p>
          </div>
        </div>
      </div>
    </div>
  );
}
