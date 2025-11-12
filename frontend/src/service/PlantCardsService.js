
export async function fetchPlantInfo() {
  try {
    const response = await fetch("http://localhost:8080/plants/info");
    if (!response.ok) {
      throw new Error(`Failed to fetch plants: ${response.status}`);
    }
    return await response.json();
  } catch (error) {
    console.error("Error fetching plants:", error);
    throw error;
  }
}
