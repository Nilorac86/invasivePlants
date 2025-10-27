import React, { useState } from "react";
import { loginUser } from "../service/LoginService";
import './Login.css';


function Login({ onLoginSuccess }) {
  const [email, setEmail] = useState(""); // Store users input.
  const [password, setPassword] = useState(""); // Store users input.
  const [error] = useState(""); //Store error from backend if something or if fetch went wrong.



// Event that prevent form from reload the users input
const handleSubmit = (e) => {
    e.preventDefault(); 
    loginUser(email, password);
}

return(
    <div className="login"> 
        <h1>
            Logga in
        </h1>

      <form className="login-form"
          onSubmit={handleSubmit}>
          <input
              type="email"
              placeholder="Email"
              // Connect inputfield to react state
              value = {email}
              onChange={(e) => setEmail (e.target.value)}/>
          

          <input
              type="password"
              placeholder="Password"
              // Connect inputfield to react state
              value = {password}
              onChange={(e) => setPassword (e.target.value)}/> 
              
          

          <button 
          className = "login-button"
          type="submit">
              Logga in
          </button>
          {error && <div className="error">{error}</div>}
      </form>
    </div>
)
}
export default Login;


