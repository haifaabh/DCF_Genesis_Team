"use client";

interface Donation {
  id: number;
  designation: string;
  quantity: string;
  address: string;
  date: string;
}

const TableRow = ({ donation }: { donation: Donation }) => {
  return (
    <tr className="border-t">
      <td className="p-3 text-[#4C506B]">{donation.designation}</td>
      <td className="p-3 text-[#4C506B]">{donation.quantity}</td>
      <td className="p-3 text-[#4C506B]">{donation.address}</td>
      <td className="p-3 text-[#4C506B]">{donation.date}</td>
      <td className="p-3 flex gap-2 justify-center">
        <button className="bg-green-500 text-white px-4 py-2 rounded-md">Approuver</button>
        <button className="bg-red-500 text-white px-4 py-2 rounded-md">Refuser</button>
      </td>
    </tr>
  );
};

export default TableRow;
