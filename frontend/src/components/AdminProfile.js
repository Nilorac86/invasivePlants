import {useEffect, useState } from "react";
import AdminRemovedPlantList from "./AdminRemovePlantList";
import { fetchRemovedPlants, VerifyRemovedPlant } from "../service/AdminRemovePlantListService";



function AdminProfileInfo({adminData}) {

  const [removedPlants, setRemovedPlants ] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);


  useEffect(() => {
    fetchRemovedPlants()
    .then(data => setRemovedPlants(data))
    .catch (error => setError(error.message))
    .finally (() => setLoading(false));
  }, []);

  const handleApprove = async (id) => {
    try{
        await VerifyRemovedPlant(id, "APPROVED");
        setRemovedPlants (prev => prev.filter(p => p.id !== id ));
    } catch (error) {
      console.error ("Fel vid godkännande", error);

    };
  }

  const handleReject = async (id) => {

    try{
      await VerifyRemovedPlant(id, "REJECTED");
      setRemovedPlants (prev => prev.filter (p => p.id !== id));
    } catch (error) {
      console.error ("Fel vid avslag");
    }
  }



      if (loading) return <p>Laddar borttagna växter...</p>;
      if (error) return <p className="error">{error}</p>;


  return (

    <div className="profile-info">
      <h3>Välkommen Admin {adminData?.email }!</h3>

      < AdminRemovedPlantList 
      plants = {removedPlants} 
      onApprove={handleApprove}
      onReject={handleReject} 
      />
    
    </div>
  );
}

export default AdminProfileInfo;