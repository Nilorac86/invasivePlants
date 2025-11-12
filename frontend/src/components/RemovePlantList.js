import { useEffect, useState } from "react";
import { fetchRemovedPlants } from "../service/RemovePlantListService";



function RemovedPlant ({plants}) {

      if (!plants || plants.length === 0) {
            return <p> Inga borttagna växter har rapporterats </p>
            
        }
    return (

        
        <div className="card-container">
            {plants.map((plant) => (
                <div className="card" key = {plant.plantName + plant.date}>
                    
                        <img className="plant-photo"
                            src={`data:image/jpeg;base64,${plant.photoAfter}`}
                            alt={plant.plantName}
                        />

                        <div className="remove-info">
                            <p>Växt: {plant.plantName}</p>
                            <p> Borttagen av: {plant.userName} </p>
                        </div>


                    </div>

                    

                          
            ))};
         
        </div>
    )
}

export default RemovedPlant;