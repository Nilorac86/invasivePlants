import "./App.css";
import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PlantCardsPage from "./Pages/PlantCardsPage";
import LoginPage from "./Pages/LoginPage";
import Header from "./components/Header";

function App() {
  const [User, setUser] = useState(null);

  const handleLoginSuccess = (userData) => {
    setUser(userData);
  };

  return (
    <Router>
      <Header user={User} />
      <Routes>
        <Route
          path="/login"
          element={<LoginPage onLoginSuccess={handleLoginSuccess} />}
        />
        <Route path="/" element={<PlantCardsPage />} />
      </Routes>
    </Router>
  );
}

export default App;
