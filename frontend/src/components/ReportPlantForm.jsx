

import React, { useState } from "react";
import "./FormBase.css";
import "./ReportPlantForm.css";
import { useLocation, useNavigate } from "react-router-dom";
import { reportPlant } from "../service/ReportPlantFormService";

function ReportPlantForm() {
    const { state } = useLocation();
    const navigate = useNavigate();
    const plant = state?.plant || {};


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

        //  Frontend validation
        const fieldErrors = {};
        if (!latitude || !coordRegex.test(latitude)) fieldErrors.latitude = "Latitude måste vara i formatet xx.yyyyy (minst 5 decimaler)";
        if (!longitude || !coordRegex.test(longitude)) fieldErrors.longitude = "Longitude måste vara i formatet xx.yyyyy (minst 5 decimaler)";
        if (!city?.trim()) fieldErrors.city = "Stad är obligatorisk";
        if (!count || parseInt(count) < 1) fieldErrors.count = "Antal måste vara minst 1";
        if (!photo) fieldErrors.photoBefore = "Du måste ladda upp en bild";

        // If frontend finds error, show instantly (no backend call)
        if (Object.keys(fieldErrors).length > 0) {
            setErrors(fieldErrors);
            setIsSubmitting(false);
            return;
        }

        // create FormData  If all fields are ok send to backend
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
            // after successful report, wait 1,5 sec and then go to profilepage
            setTimeout(() => navigate("/profile"), 1500);
        } catch (error) {
            console.error("Error från backend:", error);
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
                setGeneralError(error.message);
            } else {
                setGeneralError("Något gick fel vid rapportering");
            }
        } finally {
            setIsSubmitting(false);
        }
    };

    return (
        <div className="form-wrapper">
            <h2>Rapportera växt</h2>

            <form onSubmit={handleSubmit} className="forms-container">
                <div className="field">
                    <label>Växtnamn</label>
                    <input className="form-input" type="text" value={plant.speciesName || ""} readOnly />
                </div>

                <div className="field">
                    <label>Latitude *</label>
                    <input className="form-input"
                        type="text"
                        value={latitude}
                        onChange={(e) => setLatitude(e.target.value)}
                        placeholder="Ex. 59.12345"
                        disabled={isSubmitting}
                    />
                    {errors.latitude && <div className="error">{errors.latitude}</div>}
                </div>

                <div className="field">
                    <label>Longitude *</label>
                    <input className="form-input"
                        type="text"
                        value={longitude}
                        onChange={(e) => setLongitude(e.target.value)}
                        placeholder="Ex. 18.12345"
                        disabled={isSubmitting}
                    />
                    {errors.longitude && <div className="error">{errors.longitude}</div>}
                </div>

                <div className="field">
                    <label>Stad *</label>
                    <input className="form-input"
                        type="text"
                        value={city}
                        onChange={(e) => setCity(e.target.value)}
                        placeholder="Ange stad"
                        disabled={isSubmitting}
                    />
                    {errors.city && <div className="error">{errors.city}</div>}
                </div>

                <div className="field">
                    <label>Antal *</label>
                    <input className="form-input"
                        type="text"
                        value={count}
                        onChange={(e) => setCount(e.target.value)}
                        placeholder="Hur många växter?"
                        disabled={isSubmitting}
                    />
                    {errors.count && <div className="error">{errors.count}</div>}
                </div>

                <div className="field">
                    <label>Bild *</label>
                    <input className="form-input"
                        type="file"
                        accept="image/*"
                        onChange={(e) => setPhoto(e.target.files[0])}
                        disabled={isSubmitting}
                    />
                    {errors.photoBefore && <div className="error">{errors.photoBefore}</div>}
                </div>

                {generalError && <div className="error">{generalError}</div>}
                {success && <div className="success">{success}</div>}

                <button type="submit" className="form-btn" disabled={isSubmitting}>
                    {isSubmitting ? "Skickar..." : "Skicka rapport"}
                </button>
            </form>
        </div>
    );
}

export default ReportPlantForm;
