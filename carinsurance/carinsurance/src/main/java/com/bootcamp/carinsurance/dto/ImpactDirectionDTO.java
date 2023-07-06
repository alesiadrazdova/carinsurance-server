package com.bootcamp.carinsurance.dto;

public class ImpactDirectionDTO {

    private String name;

    public ImpactDirectionDTO(){}

    public ImpactDirectionDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
