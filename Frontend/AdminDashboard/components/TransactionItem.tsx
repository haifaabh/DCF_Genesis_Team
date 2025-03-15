import { LucideIcon } from "lucide-react";

interface TransactionProps {
  icon: LucideIcon;
  color: string;
  title: string;
  amount: string;
  place: string;
}

export default function TransactionItem({ icon: Icon, color, title, amount, place }: TransactionProps) {
  return (
    <div className="flex items-center gap-4 bg-gray-50 p-4 rounded-lg">
      <Icon className={`w-8 h-8 ${color}`} />
      <div className="flex-1">
        <p className="text-gray-800 font-medium">{title}</p>
        <p className="text-gray-500 text-sm">{place}</p>
      </div>
      <p className={`font-semibold ${amount.startsWith("+") ? "text-green-500" : "text-red-500"}`}>
        {amount}
      </p>
    </div>
  );
}
