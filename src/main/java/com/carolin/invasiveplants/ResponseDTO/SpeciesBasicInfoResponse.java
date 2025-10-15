package com.carolin.invasiveplants.ResponseDTO;


public class SpeciesBasicInfoResponse {

    private String speciesName;

    private byte[] photo;

    private String description;

    public SpeciesBasicInfoResponse() {
    }

    public SpeciesBasicInfoResponse(String speciesName, byte[] photo, String description) {
        this.speciesName = speciesName;
        this.photo = photo;
        this.description = description;
    }

    // Getters and setters
    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
