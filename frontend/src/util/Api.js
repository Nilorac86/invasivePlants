

/* General function for redirect in frontend. This is imported to ProtectedRoute, 
  and to the service (look at ProfileService).

  And in App.js the <ProtectedRoute></ProtectedRoute> needs to be included around the path. */

export async function apiGet(url) {
  try {
    const response = await fetch(`http://localhost:8080${url}`, {
      credentials: "include", // 
    });

    // Checks if status is 401 and then save current path and redirect to login.
    if (response.status === 401) {
      const currentPath = window.location.pathname;
      window.location.href = `/login?redirect=${encodeURIComponent(currentPath)}`;
      return;
    }

    if (!response.ok) {
      throw new Error(`Failed to fetch: ${response.status}`);
    }

    return await response.json();
  } catch (error) {
    console.error("API GET error:", error);
    throw error;
  }
} 

