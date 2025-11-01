// PlantCards.jsx
import React from "react";
import "./PlantCards.css";

function PlantCards({ data }) {
  const handleMoreInfo = (plant) => {
    alert(`More info about ${plant.speciesName}`);
    // or navigate to another page, open a modal, etc.
  };

  //data is an array of plants objects fetsched from backend
  return (
    <div className="card-container">
      {data.map((plant, idx) => (
        <div className="card" key={idx}>
          {/* Left side: image and plant name */}
          <div className="card-left">
            {plant.photo ? (
              <img
                className="plant-photo"
                src={plant.photo}
                alt={plant.speciesName}
              />
            ) : (
              // Placeholder box if no photo is available
              <div className="plant-photo">Photo</div>
            )}
            <div className="plant-name">{plant.speciesName}</div>
          </div>

          {/* Right side: plant description */}
          <div className="card-right">
            <p className="plant-description">{plant.description}</p>

            {/* Button to get to more info about the plant */}
            <button
              className="more-info-btn"
              onClick={() => handleMoreInfo(plant)}
            >
              More Info
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default PlantCards;
