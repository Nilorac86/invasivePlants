

import React, { useState, useEffect } from "react";
import { fetchPlantInfo } from "../service/PlantCardsService";
import PlantCards from "../components/PlantCards";

function PlantCardsPage() {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetchPlantInfo().then(setData).catch(console.error);
  }, []);

  return (
    <div className="App">
      <h2>Invasiva Växter</h2>
      {data ? <PlantCards data={data} /> : "Laddar..."}
    </div>
  );
}

export default PlantCardsPage;
