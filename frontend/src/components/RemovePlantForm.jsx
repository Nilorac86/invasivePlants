import React, { useState } from "react";
import "./RemovePlantForm.css";
import { useLocation, useNavigate } from "react-router-dom";
import { sendRemovalReport } from "../service/RemovePlantService";

function RemovePlantForm() {
    const { state } = useLocation();
    const navigate = useNavigate();
    
    const plant = state?.plant || {}; //plant data passed from card

    // Debug: log plant data received
    console.log("Plant data received:", plant);

    // state
    const [removedCount, setRemovedCount] = useState(plant?.count || "");
    const [photoAfter, setPhotoAfter] = useState(null);
    const [errors, setErrors] = useState({});
    const [generalError, setGeneralError] = useState("");
    const [success, setSuccess] = useState("");
    const [isSubmitting, setIsSubmitting] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        setErrors({});
        setGeneralError("");
        setSuccess("");
        setIsSubmitting(true);

        // Client-side validation
        const count = parseInt(removedCount, 10);
        if (!removedCount || isNaN(count) || count < 1) {
            setErrors({ removedCount: "Antal måste vara minst 1" });
            setIsSubmitting(false);
            return;
        }

        if (count > plant.count) {
            setErrors({ removedCount: `Kan inte ta bort mer än ${plant.count} växter` });
            setIsSubmitting(false);
            return;
        }

        if (!photoAfter) {
            setErrors({ photoAfter: "Du måste ladda upp en bild" });
            setIsSubmitting(false);
            return;
        }

        const formData = new FormData();
        formData.append("plantId", plant.plantId);
        formData.append("removedCount", count);
        formData.append("photoAfter", photoAfter);

        // Debug: log what we're sending
        console.log("Sending form data:", {
            plantId: plant.plantId,
            removedCount: count,
            photoAfter: photoAfter?.name
        });

        try {
            await sendRemovalReport(formData);
            setSuccess("Borttagning registrerad!");
            setTimeout(() => navigate("/profile"), 1500);
        } catch (error) {
            if (error.details) {
                const formatted = {};
                error.details.forEach((d) => {
                    const [field, msg] = d.split(":").map((s) => s.trim());
                    formatted[field] = msg;
                });
                setErrors(formatted);
            } else {
                setGeneralError(error.message || "Fel vid rapportering");
            }
        } finally {
            setIsSubmitting(false);
        }
    };

 return (
        <div className="report-form">
            <h2>Rapportera borttagen växt</h2>

            <form onSubmit={handleSubmit} className="report-form-container">

                <div className="field">
                    <label>Växtnamn</label>
                    <input type="text" value={plant.plantName || ""} readOnly />
                </div>

                <div className="field">
                    <label>Stad</label>
                    <input type="text" value={plant.city || ""} readOnly />
                </div>

                <div className="field">
                    <label>Antal rapporterat (ange antal borttagna)</label>
                    <input type="number" value={removedCount} onChange={(e) => setRemovedCount(e.target.value)} min={1} max={plant.count || undefined} />
                    {errors.removedCount && <div className="error">{errors.removedCount}</div>}
                </div>

                <div className="field">
                    <label>Rapporterad den</label>
                    <input type="text" value={plant.date || ""} readOnly />
                </div>

                <div className="field">
                    <label>Bild efter *</label>
                    <input
                        type="file"
                        accept="image/*"
                        onChange={(e) => setPhotoAfter(e.target.files[0])}
                        disabled={isSubmitting}
                    />
                    {errors.photoAfter && <div className="error">{errors.photoAfter}</div>}
                </div>

                {generalError && <div className="error">{generalError}</div>}
                {success && <div className="success">{success}</div>}

                <button type="submit" className="report-btn" disabled={isSubmitting}>
                    {isSubmitting ? "Skickar..." : "Markera borttagen"}
                </button>
            </form>
        </div>
    );

}
export default RemovePlantForm;