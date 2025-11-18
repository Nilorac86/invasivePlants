import { apiGet, apiPut } from "../util/Api";


// Fetching a list of removed plants 

export async function fetchRemovedPlants() {
  try {
    return await apiGet( "/admin/removed-plant/list");

   
  } catch (error) {
    console.error("Error fetching removed plants:", error);
    throw error;
  }
}



export const VerifyRemovedPlant = async (removedPlantId, status) => {

    return apiPut("/admin/verify", {

        removedPlantId,
        plantStatus: status
    });
}