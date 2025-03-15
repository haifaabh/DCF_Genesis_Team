"use client";

const TableHeader = () => {
  return (
    <thead>
      <tr className="bg-transparent">
        <th className="p-3 text-left text-[#A9ACBB] font-light">Désignation</th>
        <th className="p-3 text-left text-[#A9ACBB]">Quantité</th>
        <th className="p-3 text-left text-[#A9ACBB]">Adresse</th>
        <th className="p-3 text-left text-[#A9ACBB]">Date</th>
        <th className="p-3 text-center text-[#A9ACBB]">Actions</th>
      </tr>
    </thead>
  );
};

export default TableHeader;
