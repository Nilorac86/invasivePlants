//username.js

import { apiGet } from "../util/Api";

export async function getNameUser(){

    try{
        return await apiGet("/users/name");
    }catch (error){
        throw error;
    }
}