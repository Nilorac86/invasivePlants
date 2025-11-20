
import { useEffect, useState } from "react";
import RewardForm from "../components/RewardForm";
import {getRewards}from "../service/RewardListService"
import { getUserPoints } from "../service/UserPointsService.js";
import pickReward from "../service/PickRewardService.js";


function RewardPage() {
  const [rewards ,setRewards] = useState([]);
  const [userPoints, setUserPoints] = useState(0);

  useEffect(() => {
    loadRewards();
    loadPoints();
  },[]);

  async function loadRewards(){
    const data = await getRewards();
    setRewards(data);
  }

  async function loadPoints(){
    const pointsResponse = await getUserPoints();
    setUserPoints(pointsResponse.points);
  }

  async function handleRedeem(rewardId){
    try{
        await pickReward(rewardId);
        loadRewards(); // refresh list to update amount + disable buttons
        loadPoints(); // update display user points
    }catch(err){
        console.error("Fail to pick a reward: ", err);
    }
  }

  return(
    <div className="reward-page">
        <h1>Belöningar</h1>
        <h3>Dina poäng: {userPoints}</h3>
        <RewardForm rewards={rewards} userPoints = {userPoints} onRedeem= {handleRedeem}/>
    </div>
  );

}

export default RewardPage;