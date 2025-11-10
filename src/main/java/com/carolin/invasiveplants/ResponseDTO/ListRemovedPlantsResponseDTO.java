package com.carolin.invasiveplants.ResponseDTO;

import java.time.LocalDateTime;

public class ListRemovedPlantsResponseDTO {

    private String plantName;
    private String userName;
    private LocalDateTime date;
    private byte[] photoAfter;


    public ListRemovedPlantsResponseDTO(String plantName, String userName, LocalDateTime date, byte[] photoAfter) {
        this.plantName = plantName;
        this.userName = userName;
        this.date = date;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public byte[] getPhotoAfter() {
        return photoAfter;
    }

    public void setPhotoAfter(byte[] photoAfter) {
        this.photoAfter = photoAfter;
    }
}
