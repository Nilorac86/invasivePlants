package com.carolin.invasiveplants.RequestDTO;

import com.carolin.invasiveplants.Enum.RemovePlantStatus;

public class AdminVerifyRequestDTO {

    private Long reportedPlantId;
    private Long removedPlantId;
    private RemovePlantStatus plantStatus;

    public AdminVerifyRequestDTO() {
    }

    public AdminVerifyRequestDTO(Long reportedPlantId, Long removedPlantId, RemovePlantStatus plantStatus) {
        this.reportedPlantId = reportedPlantId;
        this.removedPlantId = removedPlantId;
        this.plantStatus = plantStatus;
    }

    public Long getReportedPlantId() {
        return reportedPlantId;
    }

    public void setReportedPlantId(Long reportedPlantId) {
        this.reportedPlantId = reportedPlantId;
    }

    public Long getRemovedPlantId() {
        return removedPlantId;
    }

    public void setRemovedPlantId(Long removedPlantId) {
        this.removedPlantId = removedPlantId;
    }

    public RemovePlantStatus getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(RemovePlantStatus plantStatus) {
        this.plantStatus = plantStatus;
    }
}
