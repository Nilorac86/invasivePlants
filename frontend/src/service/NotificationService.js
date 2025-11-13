// src/service/NotificationService.js

import { apiGet, apiPut } from "../util/Api";

//Fetch all notifications
export async function fetchUserNotifications(){

    try{
        return await apiGet("/notifications")
    }catch(error){
        console.error("error fetching notifications: ", error);
        throw error;
    }

}

//Mark a notification as read
export async function markNotificationAsRead(notificationId) {
  try {
    //replace {id} with the actual notification id
    return await apiPut(`/notifications/${notificationId}/read`);
  } catch (error) {
    console.error("error marking notification as read: ", error);
    throw error;
  }
}