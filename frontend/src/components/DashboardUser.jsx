//DashboardUser.jsx
 
import { Link } from "react-router-dom";
import "./DashboardUser.css";

function Dashboard({data}){

    if(!data)return <div>Loading ...</div>;

    return (
      <div className="form-container">
        <div className="points">
          <h3 id="h3">Dina poäng</h3>
          <p>{data.points}</p>
        </div>

        <div className="pending">
          <h3 id="h3">
            Borttagna växter - väntar på godkännande ({data.pendingTotal}){" "}
          </h3>
          {data.pendingPreview.map((plant) => (
            <div key={plant.id}>
              {plant.plantName} -{" "}
              {new Date(plant.removedAt).toLocaleDateString()}
            </div>
          ))}

          <Link to="/history" className="history-link">
            Visa hela historiken
          </Link>
        </div>

        <div className="approved">
          <h3 id="h3">Borttagna växter - godkända({data.approvedTotal}) </h3>
          {data.approvedPreview.map((plant) => (
            <div key={plant.id}>
              {plant.plantName} -{" "}
              {new Date(plant.removedAt).toLocaleDateString()}
            </div>
          ))}

          <Link to="/history" className="history-link">
            Visa hela historiken
          </Link>
        </div>

        <div className="gift">
          <h3 id="h3">Mottagna gåvor ({data.giftsTotal})</h3>
          {data.giftsPreview && data.giftsPreview.length > 0 ? (
            data.giftsPreview.map((reward) => (
              <div key={reward.rewardId} className="reward-item">
                <strong>{reward.rewardTitle}</strong> — {reward.points} poäng
              </div>
            ))
          ) : (
            <p>Inga gåvor än</p>
          )}

          <Link to="/history" className="history-link">
            Visa hela historiken
          </Link>

        </div>
      </div>
    ); 
}

export default Dashboard;