import "./SpeciesDetail.css"


function SpeciesDetail({ species }) {
    if (!species) return null;

    return (
        <div className="species-detail">
            <h2>{species.speciesName}</h2>

            {species.photo && (
                <img
                    src={`data:image/jpeg;base64,${species.photo}`}
                    alt={species.speciesName}
                    className="species-photo"
                />
            )}

            <p><strong>Status:</strong> {species.status}</p>

            <h3>Beskrivning</h3>
            <p>{species.description}</p>

            <h3>Biologiska egenskaper</h3>
            <p>{species.biologicalCharacteristics}</p>

            <h3>Hantera växten</h3>
            <p>{species.plantHandling}</p>
        </div>
    );
}

export default SpeciesDetail;
