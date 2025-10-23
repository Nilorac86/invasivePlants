import "./App.css";
import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PlantCardsPage from "./Pages/PlantCardsPage";
import LoginPage from "./Pages/LoginPage";


function App() {

  const [User, setUser] = useState(null);


  const handleLoginSuccess = (userData) => {

    setUser(userData);
  };

  return (
    <Router>
      <div className="App"> 
          <Routes>

            <Route path = "/login" 
            element = {<LoginPage onLoginSuccess = {handleLoginSuccess}/>} 
            />

            <Route path = "/" element = {< PlantCardsPage/>}
            />

          </Routes>
      </div>
    </Router>
  );
}

export default App;
























/* function App() {
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

export default App; */


  

/* 
function App() {
  const [user, setUser] = useState(null);

  const handleLoginSuccess = (userData) => {
    setUser(userData);
  };


}

export default App;

  */