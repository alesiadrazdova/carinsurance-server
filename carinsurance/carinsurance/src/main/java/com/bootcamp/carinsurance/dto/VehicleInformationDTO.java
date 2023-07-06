package com.bootcamp.carinsurance.dto;

import java.util.Date;

public class VehicleInformationDTO {
    private String vin;

    private int year;

    private String makeIn;

    private String model;

    private String licensePart;

    private String licenseState;

    private Date licenseExpiration;

    private int odometerValue;

    public VehicleInformationDTO(){}

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMakeIn() {
        return makeIn;
    }

    public void setMakeIn(String makeIn) {
        this.makeIn = makeIn;
    }

    public String getLicensePart() {
        return licensePart;
    }

    public void setLicensePart(String licensePart) {
        this.licensePart = licensePart;
    }

    public String getLicenseState() {
        return licenseState;
    }

    public void setLicenseState(String licenseState) {
        this.licenseState = licenseState;
    }

    public Date getLicenseExpiration() {
        return licenseExpiration;
    }

    public void setLicenseExpiration(Date licenseExpiration) {
        this.licenseExpiration = licenseExpiration;
    }

    public int getOdometerValue() {
        return odometerValue;
    }

    public void setOdometerValue(int odometerValue) {
        this.odometerValue = odometerValue;
    }

    public VehicleInformationDTO(String vin, int year, String makeIn, String licensePart, String licenseState, Date licenseExpiration, int odometerValue) {
        this.vin = vin;
        this.year = year;
        this.makeIn = makeIn;
        this.licensePart = licensePart;
        this.licenseState = licenseState;
        this.licenseExpiration = licenseExpiration;
        this.odometerValue = odometerValue;
    }
}
