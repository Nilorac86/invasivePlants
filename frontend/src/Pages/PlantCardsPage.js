

import React, { useState, useEffect } from "react";
import { fetchPlantInfo } from "../service/PlantCardsService";
import PlantCards from "../components/PlantCards";

function PlantCardsPage({ isLoggedIn }) {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetchPlantInfo().then(setData).catch(console.error);
  }, []);

  return (
    <div className="App">
      <h2>Invasiva Växter</h2>
      {data ? <PlantCards data={data}  isLoggedIn={isLoggedIn} /> : "Laddar..."}
    </div>
  );
}

export default PlantCardsPage;
