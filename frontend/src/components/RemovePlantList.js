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
                        <div className="photo-container">

                         <p>Foto vid rapportering</p>
                        <img className="plant-photo"
                            src={`data:image/jpeg;base64,${plant.photoBefore}`}
                            alt={plant.plantName}
                        />

                        <p>Foto efter borttagning</p>
                        <img className="plant-photo"
                            src={`data:image/jpeg;base64,${plant.photoAfter}`}
                            alt={plant.plantName}
                        />
                        </div>

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