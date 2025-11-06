// src/service/ReportPlantFormService.js
export async function reportPlant(formData) {
    try {
        const response = await fetch("http://localhost:8080/reportedplants/form", {
            method: "POST",
            credentials: "include", // send JWT-cookie
            body: formData,
        });

        const data = await response.json().catch(() => null); // fallback if backend dont send JSON

        if (!response.ok) {
            // backend can send: { message, details: ["city: required", ...] }
            throw data || { message: "Kunde inte skicka rapporten" };
        }

        return data;
    } catch (err) {
        console.error("ReportPlant error:", err);
        throw err;
    }
}
