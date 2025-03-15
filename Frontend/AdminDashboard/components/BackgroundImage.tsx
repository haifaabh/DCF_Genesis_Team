"use client";

import Image from "next/image";

const BackgroundImage = () => {
  return (
    <div className="absolute top-0 left-0 w-64 h-64">
      <Image src="/Polygon2.png" alt="Background Triangle" width={250} height={250} />
    </div>
  );
};

export default BackgroundImage;
