//AdminAddPlantService

import axios from "axios";

const API_URL ="http://localhost:8080/admin/species";

export const adminPostPlant= (formData) =>{
    return axios.post(API_URL, formData, {
        withCredentials: true
    }).then(res =>res.data);
};

/*
import { apiPost } from "../util/Api";

export async function adminPostPlant(){

    try{
        return await apiPost("/admin/add-plant")
    }catch (error){
        console.log("Error posting a invasive plant: ", error)
        throw error;
    }


}
    */