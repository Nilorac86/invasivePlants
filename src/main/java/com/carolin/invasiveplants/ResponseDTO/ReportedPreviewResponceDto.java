package com.carolin.invasiveplants.ResponseDTO;

import java.time.LocalDateTime;

public class ReportedPreviewResponceDto {

    private String plantName;
    private LocalDateTime dateTime;
    private Integer originalCount;
    private Integer count;

    public ReportedPreviewResponceDto() {
    }

    public ReportedPreviewResponceDto(String plantName, LocalDateTime dateTime, Integer originalCount, Integer count) {
        this.plantName = plantName;
        this.dateTime = dateTime;
        this.originalCount = originalCount;
        this.count = count;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getOriginalCount() {
        return originalCount;
    }

    public void setOriginalCount(Integer originalCount) {
        this.originalCount = originalCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
