
//get the list of reported plants from backend
export async function fetchReportedPlants() {
  try {
    const response = await fetch(
      "http://localhost:8080/reportedPlants/listAllReportedPlants",
      { 
      method:'GET',
      credentials: 'include'
      }
    );

    if (!response.ok) {
      throw new Error("Failed to fetch plants: ${response.status}");
    }
    return await response.json();
  } catch (error) {
    console.error("Error fetching plants:", error);
    throw error;
  }
}
