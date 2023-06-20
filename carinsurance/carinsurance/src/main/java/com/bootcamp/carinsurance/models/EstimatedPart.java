package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "estimated_parts")
public class EstimatedPart {
    @Id
    @Column(name = "estimated_part_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int estimatedPartId;

    @ManyToOne
    @JoinColumn(name = "type_of_parts_id", referencedColumnName = "type_of_parts_id")
    private TypeOfParts typeOfPart;

    @Column(name = "description")
    private String description;

    @Column(name = "labor_hours")
    private BigDecimal labor_hours;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "labor_rate")
    private BigDecimal laborRate;

    @ManyToOne
    @JoinColumn(name = "supplement_id", referencedColumnName = "supplement_id")
    private Supplement supplement;

    public EstimatedPart(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EstimatedPart that = (EstimatedPart) o;
        return Objects.equals(typeOfPart, that.typeOfPart) && Objects.equals(description, that.description) && Objects.equals(labor_hours, that.labor_hours) && Objects.equals(price, that.price) && Objects.equals(laborRate, that.laborRate) && Objects.equals(supplement, that.supplement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfPart, description, labor_hours, price, laborRate, supplement);
    }

    public int getEstimatedPartId() {
        return estimatedPartId;
    }

    public void setEstimatedPartId(int estimatedPartId) {
        this.estimatedPartId = estimatedPartId;
    }

    public TypeOfParts getTypeOfPart() {
        return typeOfPart;
    }

    public void setTypeOfPart(TypeOfParts typeOfParts) {
        this.typeOfPart = typeOfParts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getLabor_hours() {
        return labor_hours;
    }

    public void setLabor_hours(BigDecimal labor_hours) {
        this.labor_hours = labor_hours;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getLaborRate() {
        return laborRate;
    }

    public void setLaborRate(BigDecimal laborRate) {
        this.laborRate = laborRate;
    }

    public Supplement getSupplement() {
        return supplement;
    }

    public void setSupplement(Supplement supplement) {
        this.supplement = supplement;
    }
}