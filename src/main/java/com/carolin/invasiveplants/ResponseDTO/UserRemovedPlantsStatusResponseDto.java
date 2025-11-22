package com.carolin.invasiveplants.ResponseDTO;

import com.carolin.invasiveplants.Enum.PlantStatus;
import com.carolin.invasiveplants.Enum.RemovePlantStatus;

import java.time.LocalDateTime;

public class UserRemovedPlantsStatusResponseDto {

    private String plantName;
    private String userName;
    private LocalDateTime removedAt;
    private byte[] photoAfter;
    private RemovePlantStatus status;

    public UserRemovedPlantsStatusResponseDto() {
    }

    public UserRemovedPlantsStatusResponseDto(String plantName, String userName, LocalDateTime removedAt,
                                              byte[] photoAfter, RemovePlantStatus status) {
        this.plantName = plantName;
        this.userName = userName;
        this.removedAt = removedAt;
        this.photoAfter = photoAfter;
        this.status = status;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDateTime getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(LocalDateTime removedAt) {
        this.removedAt = removedAt;
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
}
