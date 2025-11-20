
import "./RewardForm.css";

function RewardForm({rewards= [], userPoints = 0, onRedeem = ()=>{} }) {
  return (
    <div className="beloning-form">
      {rewards.map((reward, index)=> (
        <div
          key={index}
          className={`beloning-card ${reward.affordable ? "affordable" : "not-affordable"}`}
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







