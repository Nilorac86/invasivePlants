import React, { useState } from "react";
import "./ReportPlantForm.css";
import { useLocation, useNavigate } from "react-router-dom";
import { reportPlant } from "../service/ReportPlantFormService";

function ReportPlantForm() {
    const { state } = useLocation();
    const navigate = useNavigate();
    const plant = state?.plant || {}; // Get info from previous page

    // State for form fields
    const [latitude, setLatitude] = useState("");
    const [longitude, setLongitude] = useState("");
    const [city, setCity] = useState("");
    const [count, setCount] = useState("");
    const [photo, setPhoto] = useState(null);

    // State for errors (object with error per field)
    const [errors, setErrors] = useState({});
    const [generalError, setGeneralError] = useState("");
    const [success, setSuccess] = useState("");
    const [isSubmitting, setIsSubmitting] = useState(false);

    // Regex for validation of coordinates (minimum 5 decimals)
    const coordRegex = /^\d{1,3}\.\d{5,}$/;

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors({}); // clear previous error
        setGeneralError("");
        setSuccess("");
        setIsSubmitting(true);

        // STEP 1: Frontend validation
        const fieldErrors = {}; // object for error per field

        // Validate latitude
        if (!latitude || latitude.trim() === "") {
            fieldErrors.latitude = "Latitude är obligatorisk";
        } else if (!coordRegex.test(latitude)) {
            fieldErrors.latitude = "Latitude måste vara i formatet xxx.yyyyy (minst 5 decimaler)";
        }

        // Validate longitude
        if (!longitude || longitude.trim() === "") {
            fieldErrors.longitude = "Longitude är obligatorisk";
        } else if (!coordRegex.test(longitude)) {
            fieldErrors.longitude = "Longitude måste vara i formatet xxx.yyyyy (minst 5 decimaler)";
        }

        // Validate stad
        if (!city || city.trim() === "") {
            fieldErrors.city = "Stad är obligatorisk";
        } else if (city.length > 30) {
            fieldErrors.city = "Stad får max vara 30 tecken";
        }

        // Validate antal
        if (!count || count.trim() === "") {
            fieldErrors.count = "Antal är obligatoriskt";
        } else if (isNaN(count) || parseInt(count) < 1) {
            fieldErrors.count = "Antal måste vara minst 1";
        }

        // Validate bild
        if (!photo) {
            fieldErrors.photoBefore = "Du måste ladda upp en bild";
        } else if (photo.size > 5000000) {
            fieldErrors.photoBefore = "Bilden får inte vara större än 5MB";
        }

        // If frontend finds error, show instantly (no backend call)
        if (Object.keys(fieldErrors).length > 0) {
            setErrors(fieldErrors); // save errors
            setIsSubmitting(false);
            return; // stop and dont send to backend
        }

        // STEP 2: If all fields are ok send to backend
        const formData = new FormData();
        formData.append("speciesId", plant.id);
        formData.append("latitude", latitude);
        formData.append("longitude", longitude);
        formData.append("city", city.trim());
        formData.append("count", count);
        formData.append("photoBefore", photo);

        try {
            await reportPlant(formData);
            setSuccess("Rapporten har skickats in!");
            setTimeout(() => navigate("/reportedplants"), 2000);
        } catch (error) {
            console.error("Error från backend:", error);

            // if backend sends structured errors (field: message)
            if (error.details && Array.isArray(error.details)) {
                const formatted = {};
                error.details.forEach((detail) => {
                    if (detail.includes(":")) {
                        const [field, message] = detail.split(":").map((s) => s.trim());
                        formatted[field] = message;
                    }
                });
                setErrors(formatted);
            } else if (error.message) {
                // General error from backend
                setGeneralError(error.message);
            } else {
                setGeneralError("Något gick fel vid rapportering");
            }
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="report-form">
            <h2>Rapportera växt</h2>

            <form onSubmit={handleSubmit} className="report-form-container">
                {/* Plantname (already typed, read Only) */}
                <div className="field">
                    <label>Växtnamn</label>
                    <input type="text" value={plant.speciesName || ""} readOnly />
                </div>

                {/* Latitude - at least 5 decimals */}
                <div className="field">
                    <label>Latitude *</label>
                    <input type="text" value={latitude} onChange={(e) => setLatitude(e.target.value)} placeholder="Ex. 59.12345"
                        disabled={isSubmitting}
                    />
                    {errors.latitude && <div className="error">{errors.latitude}</div>}
                </div>

                {/* Longitude - minimum 5 decimals */}
                <div className="field">
                    <label>Longitude *</label>
                    <input type="text" value={longitude} onChange={(e) => setLongitude(e.target.value)} placeholder="Ex. 18.12345"
                        disabled={isSubmitting}
                    />
                    {errors.longitude && <div className="error">{errors.longitude}</div>}
                </div>

                {/* city - text, max 30 charachters */}
                <div className="field">
                    <label>Stad *</label>
                    <input type="text" value={city} onChange={(e) => setCity(e.target.value)} placeholder="Ange stad"
                        disabled={isSubmitting}
                    />
                    {errors.city && <div className="error">{errors.city}</div>}
                </div>

                {/* count - minimum 1 */}
                <div className="field">
                    <label>Antal *</label>
                    <input type="number" value={count} onChange={(e) => setCount(e.target.value)}
                        placeholder="Hur många växter?" disabled={isSubmitting}
                    />
                    {errors.count && <div className="error">{errors.count}</div>}
                </div>

                {/* Bild - obligatorisk, max 5MB */}
                <div className="field">
                    <label>Bild *</label>
                    <input type="file" accept="image/*" onChange={(e) => setPhoto(e.target.files[0])}
                        disabled={isSubmitting}
                    />
                    {errors.photoBefore && <div className="error">{errors.photoBefore}</div>}
                </div>

                {/* Generellt felmeddelande från backend */}
                {generalError && <div className="error">{generalError}</div>}

                {/* Framgångsmeddelande */}
                {success && <div className="success">{success}</div>}

                {/* Submit-knapp */}
                <button type="submit" className="report-btn" disabled={isSubmitting}>
                    {isSubmitting ? "Skickar..." : "Skicka rapport"}
                </button>
            </form>
        </div>
    );
}

export default ReportPlantForm;