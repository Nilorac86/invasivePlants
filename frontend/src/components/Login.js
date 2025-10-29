import React, { useState } from "react";
import { loginUser } from "../service/LoginService";
import './Login.css';
import { useNavigate } from "react-router-dom";


function Login({ onLoginSuccess }) {
  const [email, setEmail] = useState(""); // Store users input.
  const [password, setPassword] = useState(""); // Store users input.
  const [error, setError] = useState(""); //Store error from backend if something or if fetch went wrong.

  
  //Varible to set navigate to another page.
  const navigate = useNavigate();


// Event that prevent form from reload the users input
const handleSubmit = async (e) => {
    e.preventDefault(); 


  if (!email || !password) {
    setError("Fyll i både e-post och lösenord");
    return;
  }

    try {
      const res = await loginUser(email, password);
      console.log("Login successful:", res);

      // Send response data to parent and navigate to profile
      if (onLoginSuccess) {
        onLoginSuccess(res);
        console.log("onLoginSuccess called"); // Debug purpose
      }
      
      // Check if cookie was set
      console.log("Cookies after login:", document.cookie);
      
      // Navigates to profilePage after successful login
      navigate('/profile');
    
    } catch (err) {
        console.error(err);
           if (err.message === "Invalid credential"){
            setError(" Wrong email or password")
        } else {
            setError(" Something went wrong, try again");
        
          
          }
        }
    }
  

return(
    <div className="login"> 
        <h1>
            Logga in
        </h1>

      {/* Login form, that takes users input to login */}
      <form className="login-form"
          onSubmit={handleSubmit}>
          <input
              type="email"
              placeholder="Email"
              // Connect inputfield to react state
              value = {email}
              onChange={(e) => setEmail (e.target.value)}/> {/* Sets user email input */}
            

          <input
              type="password"
              placeholder="Password"
              // Connect inputfield to react state
              value = {password}
              onChange={(e) => setPassword (e.target.value)}/> {/* Sets user password input */}
            
              
              {/* Button to submit login form */}
          <button 
          className = "login-button"
          type="submit">
              Logga in
          </button>
          {/* Fetches error message from backend */}
          {error && <div className="error">{error}</div>}
      </form>
    </div>
)
}

/* Export the login funktion */
export default Login;


