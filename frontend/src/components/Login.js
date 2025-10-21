import React, { useState, useEffect } from "react";


function Login({onLoginSuccess}){
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");

}

const loginUser = async (email, password) => {

    try{ // POST request to backend, email and password as request body
        const response = await fetch ("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {"Content-Type" : "application/json"}, 
            body: JSON.stringify ({
                email: email,
                password: password
            }),
            
    
            credentials: "include" 
        }) 

        const data = response.json

        return data;

    } catch (err){
    console.log ("Error, logging in", err)
    return {error: "Something went wrong" }
    }
}
    