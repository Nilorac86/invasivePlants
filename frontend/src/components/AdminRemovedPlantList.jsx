import { useState } from "react";
import "./AdminRemovedPlantList.css"



function AdminRemovedPlantList ({plants, onApprove, onReject}) {

    const [selectedPlant, setSelectedPlant] = useState(null);

        // If no plants removed
      if (!plants || plants.length === 0) {
            return (
            <div className="no-removed-plants">
                <p> Inga borttagna växter har rapporterats </p>
                </div>
            );
        }
    return (

        <> {/* Information in Admin removed plant list */}
        <div className="card-container">
            {plants.map((removedPlant) => (
                <div className="card" key = {removedPlant.plantName + removedPlant.removedAt}>
                        
                        <div>
                         <p>Foto vid rapportering</p>
                        <img className="plant-photo"
                            src={`data:image/jpeg;base64,${removedPlant.photoBefore}`}
                            alt={removedPlant.plantName}
                            onClick={() => setSelectedPlant(removedPlant)}
                        />
                        </div>

                        <div>
                        <p>Foto efter borttagning</p>
                        <img className="plant-photo"
                            src={`data:image/jpeg;base64,${removedPlant.photoAfter}`}
                            alt={removedPlant.plantName}
                            onClick = {() => setSelectedPlant(removedPlant)}
                        />
                        </div>
                    

                        <div className="remove-info">
                            <p>Växt: {removedPlant.speciesName}</p>
                            <p> Borttagen av: {removedPlant.userName} </p>
                            <p> Datum: {new Date(removedPlant.removedAt).toLocaleString()}</p>
                        </div>

                        <div className="buttons"> 
                        
                            <button onClick={() => onApprove(removedPlant.id)}> Godkänn</button>
                            <button onClick={() => onReject(removedPlant.id)}> Avslå </button>
                            
                        </div>

                    </div>
                          
            ))}
         
         </div>

            {/* Content for photos to pop up when clicked on */}
            { selectedPlant && (
                <div className="popup-overlay" onClick={() => setSelectedPlant(null)}>
                    <div className="popup-content">
                        <h3> {selectedPlant.speciesName}</h3>

                        <div className="popup-photos">
                            <img
                                src={`data:image/jpeg;base64,${selectedPlant.photoBefore}`}
                                alt="before"
                            />
                            <img
                                src={`data:image/jpeg;base64,${selectedPlant.photoAfter}`}
                                alt="after"
                            />
                        </div>
                    </div>
                </div>
            )}
        </>
    );
    
}

export default AdminRemovedPlantList;