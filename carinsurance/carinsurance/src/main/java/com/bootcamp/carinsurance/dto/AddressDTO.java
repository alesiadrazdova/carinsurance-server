package com.bootcamp.carinsurance.dto;

public class AddressDTO {
    private String city;

    private String state;

    private String zip;

    private String addressLine;

    private String typeOfAddress;

    public AddressDTO(){}

    public AddressDTO(String city, String state, String zip, String addressLine, String typeOfAddress) {
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.addressLine = addressLine;
        this.typeOfAddress = typeOfAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getTypeOfAddress() {
        return typeOfAddress;
    }

    public void setTypeOfAddress(String typeOfAddress) {
        this.typeOfAddress = typeOfAddress;
    }
}
