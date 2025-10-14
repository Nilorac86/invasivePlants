package com.carolin.invasiveplants.Entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "locations")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id")
    private Long locationId;

    @Column(precision = 9, scale = 6)  //value comes out to xxx.yyyyyy
                                       // also validates so u cant get xxxx.yyyyyy
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;

    @Column(name = "adress", length = 255)
    private String address;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "city", length = 255)
    private String city;

    @OneToMany(mappedBy = "location")
    private List<Plant> plants;

    // Constructors
    public Location() {
    }

    public Location(BigDecimal latitude, BigDecimal longitude, String address, String city) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.city = city;
    }

    // Getters and Setters
    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

}
