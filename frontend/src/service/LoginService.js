

export const loginUser = async (email, password) => {
  try {
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: { 
        "Content-Type": "application/json",
        "Accept": "application/json"
      },
      body: JSON.stringify({ email, password }),
      credentials: "include", // Include cookie (for httpOnly)
    });


    const data = await response.json();

    // Deal with error from backend (401)
    if (!response.ok) {
      
      throw data;
    }
    
    return data;

    const data = await response.json();
    
    // Store user info in localStorage if needed
    if (data.email) {
      localStorage.setItem('userEmail', data.email);
    }

    return data;
  } catch (err) {
    console.error("Login error:", err);
    throw err;
  };
}

export const logoutUser = async () => {
  try {
    const response = await fetch("http://localhost:8080/auth/logout", {
      method: "POST",
      credentials: "include", //include cookie 
    });
    // If error from backend
    if (!response.ok) {
      throw new Error("Logout failed");
    }
    //Returns answer as Json-object + catch error 
    return await response.json();
  } catch (err) {
    console.error("Logout error:", err);
    throw err;
  
  } catch (error) {
    console.error("Login error:", error);
    throw error;
  }
};
