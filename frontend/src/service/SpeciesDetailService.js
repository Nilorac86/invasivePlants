import { apiGet } from "../util/Api";

export async function fetchPlantById(id) {
  try {
    return await apiGet(`/plants/${id}`);
  } catch (error) {
    console.error("Error fetching plant by ID:", error);
    throw error;
  }
}
