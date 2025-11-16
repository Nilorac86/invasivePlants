
package com.carolin.invasiveplants.ResponseDTO;

import java.time.LocalDateTime;

public class ListRemovedPlantsResponseDTO {

    private String plantName;
    private String userName;
    private LocalDateTime removedAt;
    private byte[] photoAfter;

    public ListRemovedPlantsResponseDTO(String plantName, String userName, LocalDateTime removedAt, byte[] photoAfter) {
        this.plantName = plantName;
        this.userName = userName;
        this.removedAt = removedAt;
        this.photoAfter = photoAfter;
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
}
