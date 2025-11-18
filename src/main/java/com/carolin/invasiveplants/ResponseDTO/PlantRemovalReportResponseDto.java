package com.carolin.invasiveplants.ResponseDTO;

import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.Enum.RemovePlantStatus;

import java.time.LocalDateTime;

public class PlantRemovalReportResponseDto {

    private String speciesName;

    private byte[] photoAfter;

    private RemovePlantStatus status;

    private LocalDateTime removedAt;

    private Integer count;

    private Long reportedPlant;


    public PlantRemovalReportResponseDto() {
    }

    public PlantRemovalReportResponseDto(String speciesName, byte[] photoAfter,
                                         RemovePlantStatus status, LocalDateTime removedAt, Integer count,
                                         Long reportedPlant) {
        this.speciesName = speciesName;
        this.photoAfter = photoAfter;
        this.status = status;
        this.removedAt = removedAt;
        this.count = count;
        this.reportedPlant = reportedPlant;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public byte[] getPhotoAfter() {
        return photoAfter;
    }

    public void setPhotoAfter(byte[] photoAfter) {
        this.photoAfter = photoAfter;
    }

    public RemovePlantStatus getStatus() {
        return status;
    }

    public void setStatus(RemovePlantStatus status) {
        this.status = status;
    }

    public LocalDateTime getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(LocalDateTime removedAt) {
        this.removedAt = removedAt;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getReportedPlant() {
        return reportedPlant;
    }

    public void setReportedPlant(Long reportedPlant) {
        this.reportedPlant = reportedPlant;
    }
}
