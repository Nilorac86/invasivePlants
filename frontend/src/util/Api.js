

/* General function for redirect in frontend. This is imported to ProtectedRoute, 
  and to the service (look at ProfileService).

  And in App.js the <ProtectedRoute></ProtectedRoute> needs to be included around the path. */

export async function apiGet(url) {
  try {
    const response = await fetch(`http://localhost:8080${url}`, {
      method: "GET",
    credentials: "include", 
    headers: {
      "Content-Type": "application/json",
    },
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

// API for PUT = apiPut
export async function apiPut(url, body=null){
  
  try {
    const response = await fetch(`http://localhost:8080${url}`, {
      method: "PUT",
      credentials: "include", //include cookies for auth
      headers: {
        "Content-Type": "application/json",
      },
      body: body ? JSON.stringify(body) : null,
    });

    // Redirect to login if unauthorized
    if (response.status === 401) {
      const currentPath = window.location.pathname;
      window.location.href = `/login?redirect=${encodeURIComponent(
        currentPath
      )}`;
      return;
    }
    // Attempt to parse JSON response (may fail if no content)
    let data = null;
    try {
      data = await response.json();
    } catch {
      data = null;
    }

    if (!response.ok) {
      throw data || { message: `Kunde inte PUT:a (${response.status})` };
    }

    return data;
  } catch (error) {
    console.error("API PUT error:", error);
    throw error;
  }
}

// API for POST with JSON = apiPost
// Adds JSON headers, handles 401 redirects, parses JSON response.
export async function apiPost(url, body) {
    try {
        const response = await fetch(`http://localhost:8080${url}`, {
            method: "POST",
            credentials: "include",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body),
        });

        // If backend returns 401, redirect user to login
        if (response.status === 401) {
            const currentPath = window.location.pathname;
            window.location.href = `/login?redirect=${encodeURIComponent(currentPath)}`;
            return;
        }

        // Try parsing JSON
        let data = null;
        try {
            data = await response.json();
        } catch {
            data = null; // backend returned empty
        }

        // Throw error if not successful
        if (!response.ok) {
            throw data || { message: `Kunde inte POST:a (${response.status})` };
        }

        return data;
    } catch (error) {
    console.error("API POST JSON error: ", error);
    throw error;
    }
}

// API for POST with formdata = apiPostForm
export async function apiPostForm(url, formData) {
    try {
        const response = await fetch(`http://localhost:8080${url}`, {
            method: "POST",
            credentials: "include", // inkluderar JWT-cookie
            body: formData,
        });

        // If login demanded from backend → redirect to login
        if (response.status === 401) {
            const currentPath = window.location.pathname;
            window.location.href = `/login?redirect=${encodeURIComponent(currentPath)}`;
            return;
        }

        // try read JSON (if backend sends error text = catched)
        let data;
        try {
            data = await response.json();
        } catch {
            data = null;
        }

        if (!response.ok) {
            // Backend sends message { message, details: [...] }
            throw new Error(data?.message || `Kunde inte skicka (${response.status})`);
        }

        return data;
    } catch (error) {
        console.error("API POST Form error:", error);
        throw error;
    }
}


