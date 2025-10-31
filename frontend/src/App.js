import "./App.css";
import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PlantCardsPage from "./Pages/PlantCardsPage";
import LoginPage from "./Pages/LoginPage";
import Header from "./components/Header";
import ProfilePage from "./Pages/ProfilePage";


function App() {
  
  const [user, setUser] = useState(null);

  const handleLoginSuccess = (userData) => {
    console.log("App handleLoginSuccess with:", userData); // Debug purpose
    setUser(userData);
  };

  const handleLogout = () => {
    setUser(null);
  };

  return (
    <Router>
      <Header user={user} onLogout={handleLogout} setUser={setUser} />
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
