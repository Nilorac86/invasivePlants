/* import React, { useState } from "react";
import { loginUser } from "../service/LoginService";

function LoginPage() {
  const [user, setUser] = useState(null);

  const handleLoginSuccess = (userData) => {
    setUser(userData);
  };
  

}

export default LoginPage; */

import React from "react";
import Login from "../components/Login";

function LoginPage({ onLoginSuccess }) {
  return <Login onLoginSuccess={onLoginSuccess} />;
}

export default LoginPage;
