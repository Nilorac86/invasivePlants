package com.carolin.invasiveplants.RequestDTO;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class ReportPlantFormRequestDTO {

    @NotNull(message = "Amount of plants is required")
    @Min(value =1, message ="There have to be minimum 1 plant to make a report")
    private Integer count;

    @Lob
    @Size(max = 5_000_000, message = "Image must not exceed 5MB")
    private byte[] photoBefore;

    @NotNull(message="You have to have added what kind of plant you are reporting")
    private Long speciesId;

    @NotNull(message="You have to add a latitude nr to make an report")
    private BigDecimal latitude;

    @NotNull(message="You have to add a longitude nr to make an report")
    private BigDecimal longitude;

    @NotBlank(message ="You have to add a city, in what area the plant is found")
    @Size(max = 30, message="The city name can not contain more than 30 characters")
    private String city;

    public ReportPlantFormRequestDTO() {
    }

    public ReportPlantFormRequestDTO(String city, BigDecimal longitude, BigDecimal latitude, Long speciesId,
                                     @Size(max = 5_000_000, message = "Image must not exceed 5MB") byte[] photoBefore,
                                     Integer count) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speciesId = speciesId;
        this.photoBefore = photoBefore;
        this.count = count;
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

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
