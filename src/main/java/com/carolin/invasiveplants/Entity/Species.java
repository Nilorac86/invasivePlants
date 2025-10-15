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
    private String species_name;

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

    @OneToMany(mappedBy = "species")
    private List<Plant> plants;

    // Constructors
    public Species() {
    }


    public Species(Long speciesId, String species_name, String description, String speciesStatus,
                   String biologicalCharacteristics, String plantHandling, byte[] photo, List<Plant> plants) {
        this.speciesId = speciesId;
        this.species_name = species_name;
        this.description = description;
        this.speciesStatus = speciesStatus;
        this.biologicalCharacteristics = biologicalCharacteristics;
        this.plantHandling = plantHandling;
        this.photo = photo;
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
        return species_name;
    }

    public void setSpeciesName(String name) {
        this.species_name = species_name;
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

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
