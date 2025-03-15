'use client';

import IoTTable from '@/components/IoTTable';
import IoTIndicators from '@/components/IoTIndicators';

const IoTDashboard = () => {
  return (
    <div className="p-6 space-y-8">
      <IoTIndicators />
      <IoTTable />
    </div>
  );
};

export default IoTDashboard;
