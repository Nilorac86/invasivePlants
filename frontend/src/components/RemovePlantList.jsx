// Displays a list of removed plants with thumbnail images.
// Data is passed in as the `plants` prop, coming from an API call in RemovePlantListPage.

import { useState } from "react";
import "../components/RemovedPlantList.css";

function RemovePlantList({ plants }) {
    // Stores the currently selected plant for the popup view.
    const [selected, setSelected] = useState(null);

    // If no plants exist, show a message.
    if (!plants || plants.length === 0) {
        return <p>Inga borttagna växter har rapporterats.</p>;
    }

    return (
        <>
            <div className="removed-list">
                {plants.map((p) => (
                    <div
                        className="removed-item"
                        key={p.plantName + p.removedAt}
                        onClick={() => setSelected(p)}
                    >
                        <img
                            className="thumbnail"
                            src={`data:image/jpeg;base64,${p.photoAfter}`}
                            alt={p.plantName}
                        />

                        <div className="removed-info">
                            <h3>{p.plantName}</h3>
                            <p>Borttagen av: {p.userName}</p>
                            <p>Datum: {p.removedAt}</p>
                        </div>
                    </div>
                ))}
            </div>

            {selected && (
                <div className="popup" onClick={() => setSelected(null)}>
                    <div className="popup-inner" onClick={(e) => e.stopPropagation()}>
                        <h2>{selected.plantName}</h2>

                        <p>Foto efter borttagning</p>
                        <img
                            src={`data:image/jpeg;base64,${selected.photoAfter}`}
                            alt="Photo after"
                        />
                        <div className="removed-info">
                            <p>Borttagen av: {selected.userName}</p>
                            <p>Datum: {selected.removedAt}</p>
                            <p>Här skulle man kunna lägga till mer information som exempelvis koordinater om man vill?</p>
                        </div>

                        <button onClick={() => setSelected(null)}>Stäng</button>
                    </div>
                </div>
            )}
        </>
    );
}

export default RemovePlantList;
