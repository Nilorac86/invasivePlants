
import React from "react";
import "./RewardForm.css";

function RewardForm({rewards= [], userPoints = 0, onRedeem = ()=>{} }) {
  return (
    <div>
      {rewards.map((reward, index)=> (
        <div
          key={index}
          className={`reward-card ${reward.affordable ? "affordable" : "not-affordable"}`}
          >
          <h3>{reward.rewardTitle}</h3>
          <p>{reward.description}</p>
          <p>Poäng: {reward.points}</p>
          <p>Kvar: {reward.rewardAmount}</p>

          <button
          disabled ={!reward.affordable || reward.rewardAmount === 0}
          onClick={()=> 
            onRedeem(reward.rewardId)}
          >Lös ut</button>

        </div>
      ))}
    </div>
  );
}

export default RewardForm;

/*
function RewardForm({reward, onRedeem}){
    return(
        <div className="reward-grid">
            {reward.map((reward)=>(
                <div
                key={reward.rewardTitle}
                className={`reward-card ${reward.affordable ? "afordable" : "not-affordable"}`}
                >
                    <h3>{reward.rewardTitle}</h3>
                    <p>{reward.description}</p>
                    <p className="points">{reward.points}poäng</p>
                    <p className="amount">Antal</p>

                    <button
                    diasble={!reward.affordable}
                    onClick={()=> onRedeem(reward)}
                    >Lös ut</button>
            </div>
            ))}
        </div> 
    );
}*/






