package com.carolin.invasiveplants.ResponseDTO;

import com.carolin.invasiveplants.Enum.PlantStatus;

import java.time.LocalDateTime;

public class PlantRemovalReportResponseDto {

    private String speciesName;

    private byte[] photoBefore;

    private byte[] photoAfter;

    private PlantStatus status;

    private LocalDateTime removedAt;

    private Integer count;

    private Long reportedPlant;


    public PlantRemovalReportResponseDto() {
    }

    public PlantRemovalReportResponseDto(String speciesName, byte[] photoBefore, byte[] photoAfter, PlantStatus status,
                                         LocalDateTime removedAt, Integer count, Long reportedPlant) {
        this.speciesName = speciesName;
        this.photoBefore = photoBefore;
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

    public byte[] getPhotoBefore() {
        return photoBefore;
    }

    public void setPhotoBefore(byte[] photoBefore) {
        this.photoBefore = photoBefore;
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
