// List of all reported plants for all users to see

//import "./ListReportedPlants.css";
import { useNavigate } from "react-router-dom";


function ListReportedPlants({ data }) {
  const navigate = useNavigate();

const handleReportClick = (plant) => {
  navigate("/removeplant", { state: { plant } });
};

  //data is an array of plants objects fetsched from backend
  return (
    <div className="card-container">
      {data.map((reportedPlant, idx) => (
        <div className="card" key={idx}>

          {/* Left side: image and plant name */}
          <div className="card-left">
            {reportedPlant.photoBefore ? (
              <img
                className="plant-photo"
                src={`data:image/jpeg;base64,${reportedPlant.photoBefore}`}//base64 image fix
                alt={reportedPlant.plantName}
              />
            ) : (
              // Placeholder box if no photo is available
              <div className="plant-photo"> No Photo</div>
            )}
            <div className="plant-name">{reportedPlant.plantName}</div>
          </div>

          {/* Right side: plant info */}
          <div className="card-right">
            <p>City: {reportedPlant.city}</p>
            <p>Reported on: {new Date (reportedPlant.date).toLocaleDateString("sv-SV")}</p>
            <p>Count: {reportedPlant.count}</p>
            <button
              className="report-remove-btn"
              onClick={() => handleReportClick(reportedPlant)}
              >
              Remove Report
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ListReportedPlants;