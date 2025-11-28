package com.carolin.invasiveplants.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "species")
public class Species {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "species_id")
    private Long speciesId;

    @Column(name = "species_name",length = 255)
    private String speciesName;

    @Column(columnDefinition = "longtext")
    private String description;

    @Column(name = "status" ,length = 255)
    private String speciesStatus;

    @Column(name = "biological_charecteristics", columnDefinition = "longtext")
    private String biologicalCharacteristics;

    @Column(name = "plant_handling", columnDefinition = "longtext")
    private String plantHandling;

    @Lob
    @Column(columnDefinition = "mediumblob")
    private byte[] photo;

    @Column(name= "points_report")
    private Integer pointsReport;

    @Column(name= "points_remove")
    private Integer pointsRemove;

    @OneToMany(mappedBy = "species")
    private List<Plant> plants;

    // Constructors
    public Species() {
    }

    public Species(Long speciesId, String speciesName, String description, String speciesStatus,
                   String biologicalCharacteristics, String plantHandling, byte[] photo, Integer pointsReport,
                   Integer pointsRemove, List<Plant> plants) {
        this.speciesId = speciesId;
        this.speciesName = speciesName;
        this.description = description;
        this.speciesStatus = speciesStatus;
        this.biologicalCharacteristics = biologicalCharacteristics;
        this.plantHandling = plantHandling;
        this.photo = photo;
        this.pointsReport = pointsReport;
        this.pointsRemove = pointsRemove;
        this.plants = plants;
    }

    // Getters and Setters
    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    /*public void setSpeciesName(String name) {
        this.speciesName = speciesName;
    }*/

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeciesStatus() {
        return speciesStatus;
    }

    public void setSpeciesStatus(String speciesStatus) {
        this.speciesStatus = speciesStatus;
    }

    public String getBiologicalCharacteristics() {
        return biologicalCharacteristics;
    }

    public void setBiologicalCharacteristics(String biologicalCharacteristics) {
        this.biologicalCharacteristics = biologicalCharacteristics;
    }

    public String getPlantHandling() {
        return plantHandling;
    }

    public void setPlantHandling(String plantHandling) {
        this.plantHandling = plantHandling;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Integer getPointsReport() {
        return pointsReport;
    }

    public void setPointsReport(Integer pointsReport) {
        this.pointsReport = pointsReport;
    }

    public Integer getPointsRemove() {
        return pointsRemove;
    }

    public void setPointsRemove(Integer pointsRemove) {
        this.pointsRemove = pointsRemove;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
