import React, { useState } from "react";
import { loginUser } from "../service/LoginService";
import './Login.css';
import { useNavigate } from "react-router-dom";


function Login({ onLoginSuccess }) {
  const [email, setEmail] = useState(""); // Store users input.
  const [password, setPassword] = useState(""); // Store users input.
  const [errors, setErrors] = useState({}); //Store error from backend if something went wrong.
  const [generalError, setGeneralError] = useState("");
  const navigate = useNavigate(); //Varible to set navigate to another page.


// Event that prevent form from reload the users input
const handleSubmit = async (e) => {
    e.preventDefault();
    setErrors({}); // Resets previous errormessages.
     setGeneralError("");


    try {
        // Call backend to log in
        const res = await loginUser(email, password);

        // Notify parent and navigate to profile
        if (onLoginSuccess) onLoginSuccess(res.user);
        console.log("onLoginSuccess called"); //debug

        // Debug cookie info
        console.log("Cookies after login:", document.cookie); //debug

        navigate("/profile");

    } catch (error) {
        console.log("Error from backend:", error);

        // Convert backend strings to object for frontend use.
        if (error.details) {
            const formatted = {};

            error.details.forEach(detail => { // For each detail from backend

                if (detail.includes(":")) { // If the answer inclueds ":" then it splits field and message and takes away extra space.
                    const [field, message] = detail.split(":").map(s => s.trim());
                    formatted[field] = message; //Object formatted creates a key "field", and puts the value to message.
                } else {
                    // Generic error goes under password
                    formatted.password = detail;
                }
            });

            setErrors(formatted); // Sets error state in react and the error message show under the specific field.

            // Handle invalid credentials or user not found
            }  else if (error.message === "Invalid credentials") {
                      setGeneralError("Wrong email or password");

            // Sets general error message like "user not found".
            } else if (error.message) {
                    setGeneralError(error.message);
            } else {
                    setGeneralError("Something went wrong"); // Frontend message if no message from backend.
                }
            }

        };

        return (
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
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}/> {/* Sets user email input */}
                    {errors.email && <div className="error"> {errors.email} </div>}

                    <input
                        type="password"
                        placeholder="Password"
                        // Connect inputfield to react state
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}/> {/* Sets user password input */}
                    {errors.password && <div className="error"> {errors.password} </div>}
                    {generalError && <div className="error">{generalError}</div>}

                    {/* Button to submit login form */}
                    <button
                        className="login-button"
                        type="submit">
                        Logga in
                    </button>

                </form>
            </div>
        )
    }

    /* Export the login funktion */
    export default Login;

