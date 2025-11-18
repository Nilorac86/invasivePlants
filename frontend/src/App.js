import "./App.css";
import React, { useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PlantCardsPage from "./Pages/PlantCardsPage";
import LoginPage from "./Pages/LoginPage";
import Header from "./components/Header";
import ProfilePage from "./Pages/ProfilePage";
import ReportedPlantsPage from "./Pages/ReportedPlantsPage";
import ReportPlantFormPage from "./Pages/ReportPlantFormPage";
import ProtectedRoute from "./components/ProtectedRoute";
import AdminAddRewardPage from "./Pages/AdminAddRewardPage";





function App() {
  
  const [user, setUser] = useState(null);

  const handleLoginSuccess = (userData) => {
    console.log("App handleLoginSuccess with:", userData); // Debug purpose
    setUser(userData);
  };

  // called after logout
  const handleLogout = () => {
    setUser(null);
  };

  return (
    <Router>
      <Header user={user} onLogout={handleLogout} setUser={setUser} />
      <Routes>
        <Route path="/" element={<PlantCardsPage isLoggedIn={!!user} />} />
        <Route path="/reportedPlants" element={<ReportedPlantsPage />} />
        <Route
          path="/login"
          element={<LoginPage onLoginSuccess={handleLoginSuccess} />}
        />
          <Route path="/reportform" element={<ProtectedRoute><ReportPlantFormPage /></ProtectedRoute>} />
        <Route path="/profile" element={<ProtectedRoute><ProfilePage/></ProtectedRoute>} />
          <Route
              path="/admin/add-reward"
              element={
                  <ProtectedRoute>
                      <AdminAddRewardPage />
                  </ProtectedRoute>
              }
          />
      </Routes>
    </Router>
  );
}

export default App;
