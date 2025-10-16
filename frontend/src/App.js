import logo from './logo.svg';
import './App.css';
import React, { useState, useEffect } from "react";

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

      <h2>Plant Data</h2>
      {data ? (
        <pre>{JSON.stringify(data, null, 2)}</pre>
      ) : (
        "Loading..."
      )}

    
    </header>
  </div>
);

}

export default App;


