import {apiPost} from "../util/Api";

export async function adminAddRewardService(data) {
    return apiPost("/admin/add-reward", data);
}