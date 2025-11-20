//RewardListService.js

import { apiGet } from "../util/Api";

// Fetching a list of removed plants 

export async function getRewards() {
  try {
    return await apiGet("/rewards/list-rewards", {
      method: "GET",
      credentials: "include",
    });

   
  } catch (error) {
    console.error("Error fetching list of rewards:", error);
    throw error;
  }
}