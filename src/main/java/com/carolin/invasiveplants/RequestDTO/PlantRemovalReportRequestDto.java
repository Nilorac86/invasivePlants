package com.carolin.invasiveplants.RequestDTO;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class PlantRemovalReportRequestDto {


    private Long plantId;

    // Cannot use annotation with multipartfile.
    // But the ones that only requires text it will work fine together photo only need other validation.
    private MultipartFile photoAfter;

    @NotNull(message = "Amount of plants is required")
    @Min(value =1, message ="At least one plant is required to report a removal")
    private Integer removedCount;





    public PlantRemovalReportRequestDto(Long plantId, MultipartFile photoAfter, Integer removedCount ) {
        this.plantId = plantId;
        this.photoAfter = photoAfter;
        this.removedCount = removedCount;

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
