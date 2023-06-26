package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "other_charges")
public class OtherCharges {
    @Id
    @Column(name = "other_charges_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int otherChargesId;

    @OneToOne
    @JoinColumn(name = "supplement_id",referencedColumnName = "supplement_id")
    private Supplement supplement;

    @Column(name = "towing")
    private BigDecimal towing;

    @Column(name = "storage")
    private BigDecimal storage;

    public OtherCharges(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OtherCharges that = (OtherCharges) o;
        return Objects.equals(supplement, that.supplement) && Objects.equals(towing, that.towing) && Objects.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplement, towing, storage);
    }

    public int getOtherChargesId() {
        return otherChargesId;
    }

    public void setOtherChargesId(int otherChargesId) {
        this.otherChargesId = otherChargesId;
    }

    public Supplement getSupplement() {
        return supplement;
    }

    public void setSupplement(Supplement supplement) {
        this.supplement = supplement;
    }

    public BigDecimal getTowing() {
        return towing;
    }

    public void setTowing(BigDecimal towing) {
        this.towing = towing;
    }

    public BigDecimal getStorage() {
        return storage;
    }

    public void setStorage(BigDecimal storage) {
        this.storage = storage;
    }
}
