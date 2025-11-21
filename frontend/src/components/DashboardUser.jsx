//DashboardUser.jsx

//import { useEffect, useState } from "react";
//import { getdashboard } from "../service/DashboardUserService";
import "./DashboardUser.css";

function Dashboard({data}){

    if(!data)return <div>Loading ...</div>;

    return (
      <div className="form-container">
        <div className="points">
          <h3>Dina poäng</h3>
          <p>{data.points}</p>
        </div>

        <div className="pending">
          <h3>Borttagna växter - väntar på godkännande </h3>
          <p>{data.pendingTotal}</p>
        </div>

        <div className="approved">
          <h3>Borttagna växter - godkända</h3>
          <p>{data.approvedTotal}</p>
        </div>

        <div className="gift">
          <h3>Mottagna gåvor </h3>
          <p>{data.rewardsTotal}</p>
        </div>
      </div>
    ); 
}

export default Dashboard;