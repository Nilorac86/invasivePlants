package com.carolin.invasiveplants.ResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ReportPlantFormResponseDTO {

    private LocalDateTime date; //when report was created
    private Integer count;
    private String city;
    private String plantName;
    private String status; // current status after registration
    private BigDecimal latitude;
    private BigDecimal longitude;
    private byte[] photoBefore;

    //Constructors

    public ReportPlantFormResponseDTO() {
    }

    public ReportPlantFormResponseDTO(LocalDateTime date, Integer count, String city, String plantName, String status,
                                      BigDecimal latitude, BigDecimal longitude, byte[] photoBefore) {
        this.date = date;
        this.count = count;
        this.city = city;
        this.plantName = plantName;
        this.status = status;
        this.latitude = latitude;
        this.longitude = longitude;
        this.photoBefore = photoBefore;
    }

    // Getters and Setters

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public byte[] getPhotoBefore() {
        return photoBefore;
    }

    public void setPhotoBefore(byte[] photoBefore) {
        this.photoBefore = photoBefore;
    }
}
