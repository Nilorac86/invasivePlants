//DashboardUserService.js

import { apiGet } from "../util/Api";

// Fetching the information from backend

export async function getdashboard() {
  try {
    return await apiGet("/users/dashboard", {
      method: "GET",
      credentials: "include",
    });
  } catch (error) {
    console.error("Error fetching the dashbord list:", error);
    throw error;
  }
}
