"use client";

import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { LatLngExpression } from "leaflet";

const Map = () => {
  const position: LatLngExpression = [36.726, 3.184]; // Default center (Bab Ezzouar)

  return (
    <MapContainer center={position} zoom={13} className="w-full h-[250px] rounded-lg">
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
      <Marker position={position}>
        <Popup>Bab Ezzouar</Popup>
      </Marker>
    </MapContainer>
  );
};

export default Map;

 
