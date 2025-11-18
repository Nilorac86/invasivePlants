package com.carolin.invasiveplants.ResponseDTO;

import java.time.LocalDateTime;

public class AdminRemovedPlantsListResponseDto {

    private Long id;
    private String speciesName;
    private String userName;
    private LocalDateTime removedAt;
    private byte[] photoAfter;
    private byte[] photoBefore;

    public AdminRemovedPlantsListResponseDto() {
    }

    public AdminRemovedPlantsListResponseDto(Long id, String speciesName, String userName, LocalDateTime removedAt, byte[] photoAfter,
                                             byte[] photoBefore) {
        this.id = id;
        this.speciesName = speciesName;
        this.userName = userName;
        this.removedAt = removedAt;
        this.photoAfter = photoAfter;
        this.photoBefore = photoBefore;
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

    public byte[] getPhotoBefore() {
        return photoBefore;
    }

    public void setPhotoBefore(byte[] photoBefore) {
        this.photoBefore = photoBefore;
    }
}
