import "./App.css";
import React, { useState, useEffect } from "react";
import PlantCards from "./PlantCards";

function App() {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetch("http://localhost:8080/plants/info")
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => console.error("Error fetching data:", error));
  }, []); // [] = körs bara en gång när komponenten mountas

  return (
    <div className="App">
      <header className="App-header">
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <h2>Invasiva Växter</h2>

        {data ? <PlantCards data={data} /> : "Loading..."}
      </header>
    </div>
  );
}

export default App;
