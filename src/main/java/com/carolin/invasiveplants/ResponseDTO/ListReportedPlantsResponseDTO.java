package com.carolin.invasiveplants.ResponseDTO;

import java.math.BigInteger;
import java.time.LocalDateTime;

public class ListReportedPlantsResponseDTO {

    private Long plantId;
    private String plantName;
    private String city;
    private LocalDateTime date;
    private byte[] photoBefore;
    private Integer count;

    //should there also be latitude and longitude ?

    public ListReportedPlantsResponseDTO() {
    }

    public ListReportedPlantsResponseDTO(Long plantId, String plantName, String city, LocalDateTime date, byte[] photoBefore,
                                         Integer count) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.city = city;
        this.date = date;
        this.photoBefore = photoBefore;
        this.count = count;
    }

    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public byte[] getPhotoBefore() {
        return photoBefore;
    }

    public void setPhotoBefore(byte[] photoBefore) {
        this.photoBefore = photoBefore;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}
