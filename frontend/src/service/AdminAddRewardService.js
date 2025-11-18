//AdminAddRewardService.js
// SERVICE layer — contains backend API call logic
// Sends POST request using shared apiPost wrapper



import {apiPost} from "../util/Api";

export async function adminAddRewardService(data) {
    //Sends JSON to backend endpoint: POST /admin/admin-reward
    return apiPost("/admin/add-reward", data);
}