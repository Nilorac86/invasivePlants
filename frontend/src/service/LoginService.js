

export const loginUser = async (email, password) => {
  try {
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
      credentials: "include", // Include cookie (for httpOnly)
    });


    const data = await response.json();

    // Deal with error from backend (401)
    if (!response.ok) {
      
      throw data;
    }
    
    return data;

  
  } catch (error) {
    console.error("Login error:", error);
    throw error;
  }
};
