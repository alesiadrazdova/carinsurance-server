package com.bootcamp.carinsurance.dto;

import java.util.List;

public class VehicleConditionDTO {

    private List<ImpactDirectionDTO> impactDirections;

    private List<byte[]> photos;

    public VehicleConditionDTO(){}

    public VehicleConditionDTO(List<ImpactDirectionDTO> impactDirections, List<byte[]> photos) {
        this.impactDirections = impactDirections;
        this.photos = photos;
    }

    public List<ImpactDirectionDTO> getImpactDirections() {
        return impactDirections;
    }

    public void setImpactDirections(List<ImpactDirectionDTO> impactDirections) {
        this.impactDirections = impactDirections;
    }

    public List<byte[]> getPhotos() {
        return photos;
    }

    public void setPhotos(List<byte[]> photos) {
        this.photos = photos;
    }
}
