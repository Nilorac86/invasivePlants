import "./App.css";

import { useEffect, useState } from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import PlantCardsPage from "./Pages/PlantCardsPage";
import LoginPage from "./Pages/LoginPage";
import Header from "./components/Header";
import ProfilePage from "./Pages/ProfilePage";
import { fetchUserProfile } from "./service/ProfileService";
import ReportedPlantsPage from "./Pages/ReportedPlantsPage";
import ReportPlantFormPage from "./Pages/ReportPlantFormPage";
import RemovePlantFormPage from "./Pages/RemovePlantForm";
import ProtectedRoute from "./components/ProtectedRoute";
import AdminProfilePage from "./Pages/AdminProfilePage";
import RewardPage from "./Pages/RewardPage";
import RemovePlantListPage from "./Pages/RemovePlantListPage";
import UnifiedProfilePage from "./Pages/UnifiedProfilePage";
import AdminAddRewardPage from "./Pages/AdminAddRewardPage";
import HistoryPage from "./Pages/HistoryPage";


function App() {
  
  const [user, setUser] = useState(null);

  useEffect(() => {
    async function checkUser() {
      try {
        const userData = await fetchUserProfile();
        setUser(userData);
      } catch {
        setUser(null);
      }
    }
    checkUser();
  }, []);

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
          <Route path="/remove-plant/list" element={<RemovePlantListPage />} />
        <Route
          path="/login"
          element={<LoginPage onLoginSuccess={handleLoginSuccess} />}
        />
          <Route path="/reportform" element={<ProtectedRoute><ReportPlantFormPage /></ProtectedRoute>} />
          <Route path="/admin/add-reward" element={<ProtectedRoute><AdminAddRewardPage /></ProtectedRoute>} />
          <Route path="/removeplant" element={<ProtectedRoute><RemovePlantFormPage /></ProtectedRoute>} />
          <Route path="/profile/rewards" element={<ProtectedRoute><RewardPage /></ProtectedRoute>} />
          <Route path="/profile" element={<ProtectedRoute><UnifiedProfilePage /></ProtectedRoute>} />
          <Route path="/history" element={<ProtectedRoute><HistoryPage/></ProtectedRoute>} />
      </Routes>
    </Router>
  );
}

export default App;
