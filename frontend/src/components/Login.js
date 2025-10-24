import React, { useState } from "react";
import { loginUser } from "../service/LoginService";
import './Login.css';


function Login({ onLoginSuccess }) {
  const [email, setEmail] = useState(""); // Store users input.
  const [password, setPassword] = useState(""); // Store users input.
  const [error] = useState(""); //Store error from backend if something or if fetch went wrong.

/*   const loginUser = (email, password) => {
    // POST request to backend, email and password as requestbody
    fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" }, // Tells backend that the body of the request is json.
      body: JSON.stringify({ // Convert email and password to json string
        email: email,
        password: password
      }),

      credentials: "include" // Includes a cookie 
    })


    //Contverts the answer from backend to JavaScript objekt.
      .then((response) => response.json())
      .then((data) => {
        
        if (data.user) { // Verify if user exists
          onLoginSuccess(data.user); // If loginSuccess user data will call parentcomponent (App.js)
        } else {
          setError(data.message); // If login failed error message will be set in sate
        }
      })
      .catch((error) => { // Catches any network errors or issues
        console.log("Error logging in:", error);
        setError("Something went wrong");
      });
 }; */


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


