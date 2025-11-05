
import { apiGet } from "../util/Api";

// Fetch profile through apiGet function to redirect if not logged in. 
export async function fetchUserProfile() {
  try {
    
    return await apiGet("/auth/profile"); // Only put rest of the url to use apiGet when redirect when not logged in.

  } catch (error) {
    console.error("Error fetching user profile:", error);
    throw error;
  }
}
