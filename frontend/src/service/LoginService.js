import React, { useState, useEffect } from "react";
import Login from "../components/Login";

export const loginUser = async (email, password) => {
  try {
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
      credentials: "include", // Include cookie (for httpOnly)
    });

    // Hantera felstatus från backend (t.ex. 401)
    if (!response.ok) {
      const errData = await response.json();
      throw new Error(errData.message || "Login failed");
    }

    // Returnera svaret som JavaScript-objekt
    return await response.json();
  } catch (err) {
    console.error("Login error:", err);
    throw err;
  }
};
