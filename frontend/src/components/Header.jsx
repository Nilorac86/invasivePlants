

import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { handleLogout } from "../service/Logoutservice";
import "./Header.css";

function Header({ user, onLogout }) {
  console.log("Header render, user:", user);
  const navigate = useNavigate();

  return (
    <header className="app-header">
      <div className="logo">
          <Link to="/" className="logo-link">
        <h1>Invasive Plants App</h1>
          </Link>
      </div>
      {/* Not loggde in button if loggd in hello and the name, maybe needs to change later*/}
      <nav className="nav-link">
          {/* Länk till rapporterade växter - synlig för alla */}
          <Link to="/reportedPlants">
              <button className="nav-btn">Rapporterade växter</button>
          </Link>
          <Link to="/admin/add-reward">
              <button className="nav-btn">Admin Add Reward</button>
          </Link>

        {user ? (
          <div className="user-menu">
              {/* Länk till profil - bara för inloggade */}
              <Link to="/profile">
                  <button className="nav-btn">Min profil</button>
              </Link>

          <span>Hej, {user.email}</span> {/* user.name till user.email */}
          <button
              onClick={() => handleLogout(onLogout, navigate)}
              className="logout-btn"
            >
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
