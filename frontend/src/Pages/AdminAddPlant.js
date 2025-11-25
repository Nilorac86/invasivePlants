// AdminAddPlant.js

import {useEffect, useState} from "react";
import { adminPostPlant  } from "../service/AdminAddPlantService";
import AdminAddPlant from "../components/AdminAddPlant";


function AdminAddPlantPage(){

    const [species, setSpecies] = useState (null);

    useEffect(() => {
        adminPostPlant()
        .then(setSpecies)
        .catch(console.error);
    },[]);

    return(
        <div>
        <h2>Lägg till Invasive växt</h2>
        {species ?(
        <adminPostPlant species = {species}/>
        ) : ("Laddar...")}
    </div>
);

}

export default AdminAddPlantPage;
