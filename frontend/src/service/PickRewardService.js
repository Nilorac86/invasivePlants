import { apiPut } from "../util/Api";

const pickReward = async (rewardId)=>{

    try{
        return apiPut("/rewards/redeem/"+ rewardId,null);
    }catch(error) {
        console.error("error fetching:", error);
        throw error;
    }

};

export default pickReward