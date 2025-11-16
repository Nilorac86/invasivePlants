package com.carolin.invasiveplants.RequestDTO;

import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.Enum.RemovePlantStatus;

public class AdminVerifyRequestDTO {

    private Long reportedPlantId;
    private RemovePlantStatus plantStatus;

    public AdminVerifyRequestDTO() {
    }

    public AdminVerifyRequestDTO(RemovePlantStatus plantStatus, Long reportedPlantId) {
        this.plantStatus = plantStatus;
        this.reportedPlantId = reportedPlantId;
    }

    public RemovePlantStatus getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(RemovePlantStatus plantStatus) {
        this.plantStatus = plantStatus;
    }

    public Long getReportedPlantId() {
        return reportedPlantId;
    }

    public void setReportedPlantId(Long reportedPlantId) {
        this.reportedPlantId = reportedPlantId;
    }
}
