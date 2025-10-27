

// Fetch loggedin users profile details
export async function fetchUserProfile(userId) {
  try {
    const response = await fetch(`http://localhost:8080/auth/profile/${userId}`); // userId supose to get the users details when profile page is done.
    if (!response.ok) {
      throw new Error(`Failed to fetch user profile: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error("Error fetching user profile:", error);
    throw error;
  }
}
