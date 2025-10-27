

import React from "react";
import { Link } from "react-router-dom";
import "./Header.css";

function Header({ user }) {
  return (
    <header className="app-header">
      <div className="logo">
        <h1>Invasive Plants App</h1>
      </div>
      {/* Not loggde in button if loggd in hello and the name, maybe needs to change later*/}
      <nav className="nav-link">
        {user ? (
          <span>Hej,{user.name}</span>
        ) : (
          <Link to="/login">
            <button>Login</button>
          </Link>
        )}
      </nav>
    </header>
  );
}

export default Header;
