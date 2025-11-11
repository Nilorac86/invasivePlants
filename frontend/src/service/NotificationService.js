// src/service/NotificationService.js

import { apiGet } from "../util/Api";

export async function fetchUserNotifications(){

    try{
        return await apiGet("/notifications")
    }catch(error){
        console.error("error fetching notifications: ", error);
        throw error;
    }

}