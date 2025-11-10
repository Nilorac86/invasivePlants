
// src/service/ReportPlantFormService.js
import { apiPostForm } from "../util/Api";

export async function reportPlant(formData) {
    try {
        return await apiPostForm("/reportedplants/form", formData);
    } catch (error) {
        console.error("ReportPlant error:", error);
        throw error;
    }
}
