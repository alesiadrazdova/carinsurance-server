package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vehicle_condition")
public class VehicleCondition {
    @Id
    @Column(name = "vehicle_condition_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleConditionId;

    @OneToOne(mappedBy = "vehicleCondition")
    private Assignment assignment;

    @Column(name = "impact_direction")
    @Enumerated(EnumType.STRING)
    private ImpactDirection impact_direction;

    @Column(name = "photos")
    private String photos;

    public VehicleCondition(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleCondition that = (VehicleCondition) o;
        return Objects.equals(assignment, that.assignment) && Objects.equals(impact_direction, that.impact_direction) && Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignment, impact_direction, photos);
    }

    public int getVehicleConditionId() {
        return vehicleConditionId;
    }

    public void setVehicleConditionId(int vehicleConditionId) {
        this.vehicleConditionId = vehicleConditionId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public ImpactDirection getImpact_direction() {
        return impact_direction;
    }

    public void setImpact_direction(ImpactDirection impact_direction) {
        this.impact_direction = impact_direction;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
