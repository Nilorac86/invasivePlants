

function AdminRemovedPlantList ({plants, onApprove, onReject}) {

      if (!plants || plants.length === 0) {
            return <p> Inga borttagna växter har rapporterats </p>
            
        }
    return (

        
        <div className="card-container">
            {plants.map((removedPlant) => (
                <div className="card" key = {removedPlant.plantName + removedPlant.removedAt}>
                        <div className="photo-container">

                         <p>Foto vid rapportering</p>
                        <img className="plant-photo"
                            src={`data:image/jpeg;base64,${removedPlant.photoBefore}`}
                            alt={removedPlant.plantName}
                        />

                        <p>Foto efter borttagning</p>
                        <img className="plant-photo"
                            src={`data:image/jpeg;base64,${removedPlant.photoAfter}`}
                            alt={removedPlant.plantName}
                        />
                        </div>

                        <div className="remove-info">
                            <p>Växt: {removedPlant.spName}</p>
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
    )
}

export default AdminRemovedPlantList;