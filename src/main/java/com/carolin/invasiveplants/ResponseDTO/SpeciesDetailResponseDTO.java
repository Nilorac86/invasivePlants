package com.carolin.invasiveplants.ResponseDTO;

public class SpeciesDetailResponseDTO {

    private Long id;
    private String speciesName;
    private String description;
    private String status;
    private String biologicalCharacteristics;
    private String plantHandling;
    private byte [] photo;

    public SpeciesDetailResponseDTO(Long id, String speciesName, String description,
                                    String status, String biologicalCharacteristics,
                                    String plantHandling, byte[] photo) {
        this.id = id;
        this.speciesName = speciesName;
        this.description = description;
        this.status = status;
        this.biologicalCharacteristics = biologicalCharacteristics;
        this.plantHandling = plantHandling;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
