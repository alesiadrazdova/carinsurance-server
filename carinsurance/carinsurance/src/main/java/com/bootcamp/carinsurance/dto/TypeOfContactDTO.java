package com.bootcamp.carinsurance.dto;

public class TypeOfContactDTO {
    private String nameOfType;

    public TypeOfContactDTO(){}

    public TypeOfContactDTO(String nameOfType) {
        this.nameOfType = nameOfType;
    }

    public String getNameOfType() {
        return nameOfType;
    }

    public void setNameOfType(String nameOfType) {
        this.nameOfType = nameOfType;
    }
}
