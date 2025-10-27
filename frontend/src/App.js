import "./App.css";
import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PlantCardsPage from "./Pages/PlantCardsPage";
import LoginPage from "./Pages/LoginPage";
import Header from "./components/Header";
import ProfilePage from "./Pages/ProfilePage";


function App() {
  
  const [user, setUser] = useState(null);

  const handleLoginSuccess = (userData) => {
    setUser(userData);
  };

  return (
    <Router>
      <Header user={user} />
      <Routes>
        <Route path ="/" element={<PlantCardsPage />} />
        <Route path="/login" element={<LoginPage onLoginSuccess={handleLoginSuccess} />}
        />
        <Route path ="/profile" element={<ProfilePage userId={user?.id}/>} />
      </Routes>
    </Router>
  );
}

export default App;
