import React, { useState, useEffect } from "react";
import { Navigate, useLocation } from "react-router-dom";
import {apiGet} from "../util/Api";

// 
function ProtectedRoute ({children}) {
    const [authorized, setAuthorized] = useState (null);
    const location = useLocation(); // Saves  before redirected to login.


    useEffect(() => {

        apiGet("/auth/profile") 
        .then (() => setAuthorized (true)) // User is authenticated 
        .catch (() => setAuthorized (false)); // User not authenticated, error 401

    }, []);

    if (authorized === null) {
        return <p>Laddar....</p>
    }

     if (!authorized){
    return <Navigate to={`/login?redirect=${location.pathname}`} replace />; //If not authenticated redirect to login. 
    
     }

     return children;
    }

    export default ProtectedRoute;

