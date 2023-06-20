package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "supplement")
public class Supplement {

    @Id
    @Column(name = "supplement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplementId;

    @OneToOne
    @JoinColumn(name = "assignment_id",referencedColumnName = "assignment_id")
    private Assignment assignment;

    @OneToOne
    @JoinColumn(name = "other_charges_id",referencedColumnName = "other_charges_id")
    private OtherCharges otherCharges;

    @OneToMany(mappedBy = "supplement")
    private List<EstimatedPart> estimatedParts;

    public Supplement(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplement that = (Supplement) o;
        return Objects.equals(assignment, that.assignment) && Objects.equals(otherCharges, that.otherCharges);
    }

    public List<EstimatedPart> getEstimatedParts() {
        return estimatedParts;
    }

    public void setEstimatedParts(List<EstimatedPart> estimatedParts) {
        this.estimatedParts = estimatedParts;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignment, otherCharges);
    }

    public OtherCharges getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(OtherCharges otherCharges) {
        this.otherCharges = otherCharges;
    }

    public int getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(int supplementId) {
        this.supplementId = supplementId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
