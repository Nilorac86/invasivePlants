//ReportedPlantsPage

import React, { useState, useEffect } from "react";
import { fetchReportedPlants } from "../service/ListReportedPlantsService";
import ListReportedPlants from "../components/ListReportedPlants";


function ReportedPlantsPage() {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetchReportedPlants().then(setData).catch(console.error);
  }, []);

  return (
    <div className="ReportedPlants">
      <h2>Reported Invasiv Plants</h2>
      {data ? <ListReportedPlants data = {data} /> : "Laddar..."}
    </div>
  );
}

export default ReportedPlantsPage;
