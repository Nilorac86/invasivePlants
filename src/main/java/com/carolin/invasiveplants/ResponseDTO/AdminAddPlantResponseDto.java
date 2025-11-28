package com.carolin.invasiveplants.ResponseDTO;

public class AdminAddPlantResponseDto {

    private String speciesName;
    private String description;
    private String speciesStatus;
    private String biologicalCharacteristics;
    private String plantHandling;
    private String photoBase64;

    private Integer pointsReport;
    private Integer pointsRemove;

    public AdminAddPlantResponseDto() {
    }

    public AdminAddPlantResponseDto(String speciesName, String description, String speciesStatus,
                                    String biologicalCharacteristics, String plantHandling, String photoBase64,
                                    Integer pointsReport, Integer pointsRemove) {
        this.speciesName = speciesName;
        this.description = description;
        this.speciesStatus = speciesStatus;
        this.biologicalCharacteristics = biologicalCharacteristics;
        this.plantHandling = plantHandling;
        this.photoBase64 = photoBase64;
        this.pointsReport = pointsReport;
        this.pointsRemove = pointsRemove;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeciesStatus() {
        return speciesStatus;
    }

    public void setSpeciesStatus(String speciesStatus) {
        this.speciesStatus = speciesStatus;
    }

    public String getBiologicalCharacteristics() {
        return biologicalCharacteristics;
    }

    public void setBiologicalCharacteristics(String biologicalCharacteristics) {
        this.biologicalCharacteristics = biologicalCharacteristics;
    }

    public String getPlantHandling() {
        return plantHandling;
    }

    public void setPlantHandling(String plantHandling) {
        this.plantHandling = plantHandling;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    public Integer getPointsReport() {
        return pointsReport;
    }

    public void setPointsReport(Integer pointsReport) {
        this.pointsReport = pointsReport;
    }

    public Integer getPointsRemove() {
        return pointsRemove;
    }

    public void setPointsRemove(Integer pointsRemove) {
        this.pointsRemove = pointsRemove;
    }
}
