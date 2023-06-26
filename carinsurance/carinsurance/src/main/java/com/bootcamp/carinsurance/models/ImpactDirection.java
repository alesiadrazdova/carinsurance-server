package com.bootcamp.carinsurance.models;

import javax.persistence.*;

@Entity
@Table(name = "impact_direction")
public class ImpactDirection {
    @Id
    @Column(name = "impact_direction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int impactDirectionId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_condition_id", referencedColumnName = "vehicle_condition_id")
    private VehicleCondition vehicleCondition;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private NameOfImpactDirection name;

    public ImpactDirection(){}

    public int getImpactDirectionId() {
        return impactDirectionId;
    }

    public void setImpactDirectionId(int impactDirectionId) {
        this.impactDirectionId = impactDirectionId;
    }

    public VehicleCondition getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VehicleCondition vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public NameOfImpactDirection getName() {
        return name;
    }

    public void setName(NameOfImpactDirection name) {
        this.name = name;
    }
}
