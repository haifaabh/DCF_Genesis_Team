import { MoreVertical } from 'lucide-react';

const IoTTable = () => {
  const data = [
    { aliment: 'Dattes', quantity: '5 Kg', adresse: 'Bab Ezzouar', date: 'Il y a 10 min' },
    { aliment: 'Lait', quantity: '2 L', adresse: 'Oued Smar', date: 'Il y a 35 min' },
    { aliment: 'Dattes', quantity: '5 Kg', adresse: 'Bab Ezzouar', date: 'Il y a 10 min' },
    { aliment: 'Lait', quantity: '2 L', adresse: 'Oued Smar', date: 'Il y a 35 min' },
    { aliment: 'Dattes', quantity: '5 Kg', adresse: 'Bab Ezzouar', date: 'Il y a 10 min' },
    { aliment: 'Lait', quantity: '2 L', adresse: 'Oued Smar', date: 'Il y a 35 min' },
  ];

  return (
    <div className="bg-white rounded-2xl shadow-lg p-6">
      <h3 className="text-lg font-semibold mb-4">Historique des relevés</h3>
      <table className="w-full text-left border-collapse">
        <thead>
          <tr className="text-gray-500 border-b">
            <th className="p-3">Aliment</th>
            <th className="p-3">Quantité</th>
            <th className="p-3">Adresse</th>
            <th className="p-3">Date</th>
            <th className="p-3"></th>
          </tr>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index} className="border-b text-gray-700">
              <td className="p-3">{item.aliment}</td>
              <td className="p-3">{item.quantity}</td>
              <td className="p-3">{item.adresse}</td>
              <td className="p-3">{item.date}</td>
              <td className="p-3 text-right">
                <MoreVertical className="cursor-pointer text-gray-400 hover:text-gray-600" />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default IoTTable;
