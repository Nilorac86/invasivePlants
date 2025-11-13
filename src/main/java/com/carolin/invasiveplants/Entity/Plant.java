package com.carolin.invasiveplants.Entity;

import com.carolin.invasiveplants.Enum.PlantStatus;
import jakarta.persistence.*;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reporting_plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plant_id")
    private Long plantId;

    @Lob
    @Column(name = "photo_before", columnDefinition = "mediumblob")
    private byte[] photoBefore;

    @Enumerated(EnumType.STRING)
    private PlantStatus status;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "reported_by_user_id")
    private User reportedBy;

    @OneToMany(mappedBy = "reportedPlant")
    private List<RemovedPlant> removedPlants = new ArrayList<>();

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "count")
    private Integer count;

    // Constructors
    public Plant() {
    }

    public Plant(byte[] photoBefore, PlantStatus status, Species species, Location location, User reportedBy, LocalDateTime dateTime, int count) {
        this.photoBefore = photoBefore;
        this.status = status;
        this.species = species;
        this.location = location;
        this.reportedBy = reportedBy;
        this.dateTime = dateTime;
        this.count = count;
    }

    // Getters and Setters
    public Long getPlantId() {
        return plantId;
    }

    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }

    public byte[] getPhotoBefore() {
        return photoBefore;
    }

    public void setPhotoBefore(byte[] photoBefore) {
        this.photoBefore = photoBefore;
    }

    public PlantStatus getStatus() {
        return status;
    }

    public void setStatus(PlantStatus status) {
        this.status = status;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(User reportedBy) {
        this.reportedBy = reportedBy;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
