// History.jsx

import {useState} from "react";
import "./History.css"

export default function History({data}){

    const [activ, setActiv]= useState("pending");// default list

    const renderList = () =>{

        switch(activ){

            case "pending":
                return data.pendingPreview.map(item =>(
                    <div key = {item.id}>
                        {item.plantName} - {new Date(item.removedAt).toLocaleTimeString()}
                    </div>
                ));

            case "approved":
                return data.approvedPreview.map(item =>(
                    <div key = {item.id}>
                        {item.plantName} - {new Date(item.removedAt).toLocaleTimeString()}
                    </div>
                ));  
            
            case "gifts":
                return data.giftsPreview.lenght >0 
                ? data.giftsPreview.map(r => (
                  <div key={r.rewardId}>
                    {r.rewardTitle} -{r.points} poäng
                  </div>
                )) 
            : <p>Inga gåvor ännu</p>;  
            
            default:
                return null;
        }
    };

    return (
        <div className="history-container">
            <h2>Historik</h2>
        
            <div className="tabs">

                <button 
                className={activ === "pending" ? "active" : ""}
                onClick={() => setActiv("pending")}
                >Väntar på godkännande ({data.pendingTotal})
                </button>    

                <button 
                className={activ === "approved" ? "active": ""}
                onClick={() => setActiv("approved")}
                >Godkännande ({data.approvedTotal})
                </button>  

                <button
                className={activ === "gifts" ? "active" : ""}
                onClick={() => setActiv("gifts")}
                >Gåvor ({data.giftsTotal})
                </button> 

            </div>
        
            <div className="list">{renderList()}</div>
        </div>    
    );

}