// src/Pages/RemovePlantListPage.js
// Page that loads data from backend and passes it to RemovePlantList.


import React, { useState, useEffect } from "react";
import { fetchRemovedPlants } from "../service/RemovePlantListService";
import RemovePlantList from "../components/RemovePlantList";

function RemovePlantListPage() {
    // Holds the list of plants loaded from the backend API.
    const [plants, setPlants] = useState(null);

    // Loads data when the component is first rendered.
    useEffect(() => {
        fetchRemovedPlants()
            .then(setPlants)         // Saves the returned list in state
            .catch(console.error);
    }, []);

    return (
        <div className="App">
            <h2>Borttagna växter</h2>
            {plants ? <RemovePlantList plants={plants} /> : "Laddar..."}
        </div>
    );
}

export default RemovePlantListPage;
