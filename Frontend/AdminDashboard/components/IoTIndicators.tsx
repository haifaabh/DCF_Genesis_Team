const IoTIndicators = () => {
    return (
      <div className="bg-white rounded-2xl shadow-lg p-6">
        <h2 className="text-xl font-semibold">Capteurs IoT :</h2>
        <div className="mt-4 p-4 bg-gray-100 rounded-lg">
          <p className="font-medium">Affichage des indicateurs relevés</p>
          <div className="flex justify-between items-center mt-2">
            <p>10 Kg de Dattes - Suivi</p>
            <button className="bg-yellow-400 text-white px-3 py-1 rounded-md">Historique</button>
          </div>
          <p className="text-gray-500">Dernier scan il y a 15 min</p>
          <div className="flex mt-4 gap-4">
            <div className="bg-yellow-100 p-4 rounded-lg w-1/2">
              <p className="text-gray-500">Température</p>
              <p className="text-xl font-bold">4.2°C</p>
              <span className="text-green-500">Normale</span>
            </div>
            <div className="bg-yellow-100 p-4 rounded-lg w-1/2">
              <p className="text-gray-500">Poids</p>
              <p className="text-xl font-bold">9.8 Kg</p>
              <span className="text-green-500">Vérifié</span>
            </div>
          </div>
        </div>
      </div>
    );
  };
  
  export default IoTIndicators;
  