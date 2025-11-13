package com.carolin.invasiveplants.Entity;


import com.carolin.invasiveplants.Enum.PlantStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "removed_plants")
public class RemovedPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="removed_plant_id")
    private Long removedPlantId;

    @Lob
    @Column(name= "photo_after", columnDefinition = "mediumblob")
    private byte[] photoAfter;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private PlantStatus status;

    @Column(name = "date_time")
    private LocalDateTime removedAt;

    @Column(name="count")
    private Integer count;


    @JoinColumn (name = "reported_plant_id")
    private Long reportedPlantId;


    @ManyToOne
    @JoinColumn(name = "removed_by_user_id")
    private User removedBy;


    public RemovedPlant() {
    }

    public RemovedPlant(Long removedPlantId, byte[] photoAfter, PlantStatus status, LocalDateTime removedAt,
                        Integer count, Long reportedPlantId, User removedBy) {
        this.removedPlantId = removedPlantId;
        this.photoAfter = photoAfter;
        this.status = status;
        this.removedAt = removedAt;
        this.count = count;
        this.reportedPlantId = reportedPlantId;
        this.removedBy = removedBy;
    }


    public Long getRemovedPlantId() {
        return removedPlantId;
    }

    public void setRemovedPlantId(Long removedPlantId) {
        this.removedPlantId = removedPlantId;
    }

    public byte[] getPhotoAfter() {
        return photoAfter;
    }

    public void setPhotoAfter(byte[] photoAfter) {
        this.photoAfter = photoAfter;
    }

    public PlantStatus getStatus() {
        return status;
    }

    public void setStatus(PlantStatus status) {
        this.status = status;
    }

    public LocalDateTime getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(LocalDateTime removedAt) {
        this.removedAt = removedAt;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getReportedPlantId() {
        return reportedPlantId;
    }

    public void setReportedPlantId(Long reportedPlantId) {
        this.reportedPlantId = reportedPlantId;
    }

    public User getRemovedBy() {
        return removedBy;
    }

    public void setRemovedBy(User removedBy) {
        this.removedBy = removedBy;
    }
}
