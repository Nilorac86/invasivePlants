//AdminAddPlantService

import { apiPost, apiPostForm } from "../util/Api";

export async function adminPostPlant(formData){

    try{
        return await apiPostForm("/admin/add-plant", formData)
    }catch (error){
        console.log("Error posting a invasive plant: ", error)
        throw error;
    }
}
    