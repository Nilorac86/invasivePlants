
import { apiGet } from "../util/Api";

export async function getUserPoints(){

     try {
       return await apiGet("/rewards/points");
     } catch (error) {
       console.error("error fetching users points: ", error);
       throw error;
     }

    
}