package com.carolin.invasiveplants.RequestDTO;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class PlantRemovalReportRequestDto {


    private Long plantId;

    @Lob
    @Size(max = 5_000_000, message = "Image must not exceed 5MB")
    private MultipartFile photoAfter;

    private Integer removedCount;




    public PlantRemovalReportRequestDto(Long plantId, MultipartFile photoAfter, Integer count ) {
        this.plantId = plantId;
        this.photoAfter = photoAfter;
        this.removedCount = count;


    }


    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public MultipartFile getPhotoAfter() {
        return photoAfter;
    }

    public void setPhotoAfter(MultipartFile photoAfter) {
        this.photoAfter = photoAfter;
    }

    public Integer getRemovedCount() {
        return removedCount;
    }

    public void setRemovedCount(Integer removedCount) {
        this.removedCount = removedCount;
    }
}
