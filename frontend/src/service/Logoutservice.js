

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
  }
};

export const handleLogout = async (onLogout, navigate) => {
  try {
    await logoutUser();
    onLogout(); //clear user state in App
    navigate("/"); //redirect to home page
  } catch (err) {
    console.error("Logout failed:", err);
  }
};


