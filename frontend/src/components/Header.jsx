

import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { logoutUser } from "../service/LoginService";
import "./Header.css";

function Header({ user, onLogout }) {
  console.log("Header render, user:", user);
  const navigate = useNavigate();
  
  const handleLogout = async () => {
    try {
      await logoutUser();
      onLogout(); //clear user state in App
      navigate("/"); //redirect to home page
    } catch (err) {
      console.error("Logout failed:", err);
    }
  };

  return (
    <header className="app-header">
      <div className="logo">
        <h1>Invasive Plants App</h1>
      </div>
      {/* Not loggde in button if loggd in hello and the name, maybe needs to change later*/}
      <nav className="nav-link">
        {user ? (
          <div className="user-menu">
          <span>Hej, {user.email}</span> {/* user.name till user.email */}
          <button onClick={handleLogout} className="logout-btn">
            Logga ut
          </button>
          </div>
        ) : (
          <Link to="/login">
            <button className="loginbtn">Login</button>
          </Link>
        )}
      </nav>
    </header>
  );
}

export default Header;
