package com.bootcamp.carinsurance.dto;

import java.math.BigDecimal;

public class OtherChargesDTO {
    private BigDecimal towing;
    private BigDecimal storage;
    public OtherChargesDTO(){}

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
