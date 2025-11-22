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
                        {item.plantName} - {new Date(item.removedAt).toLocaleDateString()}
                    </div>
                ));

            case "approved":
                return data.approvedPreview.map(item =>(
                    <div key = {item.id}>
                        {item.plantName} - {new Date(item.removedAt).toLocaleDateString()}
                    </div>
                ));  
            
            case "gifts":
                if(!data.giftsPreview|| data.giftsPreview.length===0)
                    return <p>Inga gåvor ännu</p>;

                return data.giftsPreview.map(gift => (
                  <div key={gift.rewardId}>
                    {gift.rewardTitle} -{gift.points} poäng
                  </div>
                )) ;
            
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
                >Godkända ({data.approvedTotal})
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