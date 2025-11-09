package com.carolin.invasiveplants.ResponseDTO;

import java.time.LocalDateTime;

public class PlantRemovalReportResponseDto {

    private Long plantId;

    private String Species_name;

    private byte[] photoAfter;

    private String status;

    private LocalDateTime removedAt;


    public PlantRemovalReportResponseDto() {
    }

    public PlantRemovalReportResponseDto(Long plantId, String species_name, byte[] photoAfter, String status,
                                         LocalDateTime removedAt) {
        this.plantId = plantId;
        Species_name = species_name;
        this.photoAfter = photoAfter;
        this.status = status;
        this.removedAt = removedAt;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public String getSpecies_name() {
        return Species_name;
    }

    public void setSpecies_name(String species_name) {
        Species_name = species_name;
    }

    public byte[] getPhotoAfter() {
        return photoAfter;
    }

    public void setPhotoAfter(byte[] photoAfter) {
        this.photoAfter = photoAfter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(LocalDateTime removedAt) {
        this.removedAt = removedAt;
    }
}
