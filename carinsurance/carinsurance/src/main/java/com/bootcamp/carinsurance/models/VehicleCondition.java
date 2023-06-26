package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle_condition")
public class VehicleCondition {
    @Id
    @Column(name = "vehicle_condition_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleConditionId;

    @OneToOne(mappedBy = "vehicleCondition",cascade = CascadeType.ALL)
    private Assignment assignment;

    @OneToMany(mappedBy = "vehicleCondition",cascade = CascadeType.ALL)
    private List<ImpactDirection> impact_directions;

    @Column(name = "photos")
    private String photos;

    public VehicleCondition(){}

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

    public List<ImpactDirection> getImpact_directions() {
        return impact_directions;
    }

    public void setImpact_directions(List<ImpactDirection> impact_directions) {
        this.impact_directions = impact_directions;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }
}
