import { apiGet } from "../util/Api";

// Fetching a list of removed plants 

export async function fetchRemovedPlants() {
  try {
    return await apiGet( "/remove-plant/list")

   
  } catch (error) {
    console.error("Error fetching removed plants:", error);
    throw error;
  }
}