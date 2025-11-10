package com.carolin.invasiveplants.RequestDTO;

import com.carolin.invasiveplants.Enum.PlantStatus;

public class AdminVerifyRequestDTO {

    private Long reportedPlantId;
    private PlantStatus plantStatus;

    public AdminVerifyRequestDTO() {
    }

    public AdminVerifyRequestDTO(PlantStatus plantStatus, Long reportedPlantId) {
        this.plantStatus = plantStatus;
        this.reportedPlantId = reportedPlantId;
    }

    public PlantStatus getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(PlantStatus plantStatus) {
        this.plantStatus = plantStatus;
    }

    public Long getReportedPlantId() {
        return reportedPlantId;
    }

    public void setReportedPlantId(Long reportedPlantId) {
        this.reportedPlantId = reportedPlantId;
    }
}
