
import React from "react";
import { Link, useNavigate } from "react-router-dom";
import { handleLogout } from "../service/Logoutservice";
import "./Header.css";

function Header({ user, onLogout }) {
    console.log("Header render, user:", user);
    const navigate = useNavigate();

    // Check if logged-in user is admin
    const isAdmin =
        user?.role?.includes("ADMIN") ||
        user?.roles?.some(r => r.includes("ADMIN"));

    return (
        <header className="app-header">
            <div className="logo">
                <Link to="/" className="logo-link">
                    <h1>Invasive Plants App</h1>
                </Link>
            </div>

            <nav className="nav-bar">

                {/* Plantlist – shows for all */}
                <Link to="/" className="nav-link">
                    Växtlista
                </Link>

                {/* --- DROPDOWN RAPPORTER --- */}
                <div className="dropdown">
                    <button className="dropbtn">Rapporter ▾</button>

                    <div className="dropdown-content">
                        <Link to="/reportedPlants">Lista på tillagda växter</Link>
                        <Link to="/remove-plant/list">Lista på borttagna växter</Link>
                    </div>
                </div>

                {/* Rewards – Shows for all */}
                <Link to="./Pages/RewardPage" className="nav-link">
                    Lista Belöningar
                </Link>

                {/* Admin-only */}
                {isAdmin && (
                    <Link to="/admin/add-reward" className="nav-link">
                        Admin lägg till belöningar
                    </Link>
                )}

                {/* User menu */}
                {user ? (
                    <div className="user-menu">

                        {isAdmin ? (
                            <Link to="/admin/profile" className="nav-link">Adminprofil</Link>
                        ) : (
                            <Link to="/profile" className="nav-link">Min profil</Link>
                        )}

                        <span>Hej, {user.email}</span>

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
