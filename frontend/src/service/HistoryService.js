//history.js

import { apiGet } from "../util/Api";

export async function getHistory() {
  try {
    return await apiGet("/user/history");
  } catch (error) {
    console.error("error fetching lists of history: ", error);
    throw error;
  }
}