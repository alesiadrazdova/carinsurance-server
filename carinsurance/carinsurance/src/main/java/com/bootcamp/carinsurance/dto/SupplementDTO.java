package com.bootcamp.carinsurance.dto;

import java.util.List;

public class SupplementDTO {
    private List<OtherChargesDTO> otherChargesList;
    public SupplementDTO(){}

    public List<OtherChargesDTO> getOtherChargesList() {
        return otherChargesList;
    }

    public void setOtherChargesList(List<OtherChargesDTO> otherChargesList) {
        this.otherChargesList = otherChargesList;
    }
}
