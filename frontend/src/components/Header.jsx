import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { handleLogout } from "../service/Logoutservice";
import "./Header.css";

function Header({ user, onLogout }) {
    const navigate = useNavigate();
    const [mobileOpen, setMobileOpen] = useState(false);

    // Check if logged-in user is admin
    const isAdmin =
        user?.role?.includes("ADMIN") ||
        user?.roles?.some(r => r.includes("ADMIN"));

    return (
        <header className="app-header">

            {/* LOGO */}
            <div className="logo">
                <Link to="/" className="logo-link">
                    <h1>Invasive Plants App</h1>
                </Link>
            </div>

            {/* DESKTOP NAV */}
            {/* Plantlist – shows for all */}
            <nav className="nav-bar desktop-only">
                <Link to="/" className="nav-link">Växtlista</Link>

                {/* Plantlist – shows for all */}
                <Link to="/" className="nav-link">
                    Växtlista
                </Link>

                {/* Link to register user */}
                <Link to="/register" className="nav-link">
                    Registrera
                </Link>



                {/* --- DROPDOWN RAPPORTER --- */}
                <div className="dropdown">
                    <button className="dropbtn">Rapporter ▾</button>
                    <div className="dropdown-content">
                        <Link to="/reportedPlants">Lista på tillagda växter</Link>
                        <Link to="/remove-plant/list">Lista på borttagna växter</Link>
                    </div>
                </div>

                {/* Admin-only */}
                {isAdmin && (
                    <Link to="/admin/add-reward" className="nav-link">
                        Admin lägg till belöningar
                    </Link>
                )}

                {/* User menu */}
                {user ? (
                    <>
                        <Link to="/profile" className="nav-link">Profil</Link>

                        {/* Rewards – only visible for regular users */}
                        {!isAdmin && (
                            <Link to="/profile/rewards" className="nav-link">
                                Lista Belöningar
                            </Link>
                        )}

                        <button
                            onClick={() => handleLogout(onLogout, navigate)}
                            className="logout-btn"
                        >
                            Logga ut
                        </button>
                    </>
                ) : (
                    <Link to="/login">
                        <button className="loginbtn">Login</button>
                    </Link>
                )}
            </nav>

            {/* MOBILE NAV ICON */}
            <button
                className="mobile-icon mobile-only"
                onClick={() => setMobileOpen(!mobileOpen)}
            >
                <i className="fa fa-bars"></i>
            </button>

            {/* MOBILE MENU */}
            {mobileOpen && (
                <div className="mobile-menu mobile-only">

                    <Link to="/" className="mobile-link" onClick={() => setMobileOpen(false)}>
                        Växtlista
                    </Link>

                    <Link to="/reportedPlants" className="mobile-link" onClick={() => setMobileOpen(false)}>
                        Lista på tillagda växter
                    </Link>

                    <Link to="/remove-plant/list" className="mobile-link" onClick={() => setMobileOpen(false)}>
                        Lista på borttagna växter
                    </Link>

                    {isAdmin && (
                        <Link to="/admin/add-reward" className="mobile-link" onClick={() => setMobileOpen(false)}>
                            Admin lägg till belöningar
                        </Link>
                    )}

                    {user ? (
                        <>
                            <Link to="/profile" className="mobile-link" onClick={() => setMobileOpen(false)}>
                                Profil
                            </Link>

                            {!isAdmin && (
                                <Link to="/profile/rewards" className="mobile-link" onClick={() => setMobileOpen(false)}>
                                    Lista Belöningar
                                </Link>
                            )}

                            <button
                                className="mobile-logout"
                                onClick={() => {
                                    handleLogout(onLogout, navigate);
                                    setMobileOpen(false);
                                }}
                            >
                                Logga ut
                            </button>
                        </>
                    ) : (
                        <Link to="/login" onClick={() => setMobileOpen(false)}>
                            <button className="mobile-login">Login</button>
                        </Link>
                    )}
                </div>
            )}

        </header>
    );
}

export default Header;
