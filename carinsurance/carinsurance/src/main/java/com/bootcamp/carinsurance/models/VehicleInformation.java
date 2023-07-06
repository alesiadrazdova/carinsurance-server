package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "vehicle_information")
public class VehicleInformation {
    @Id
    @Column(name = "vehicle_information_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleInformationId;

    @OneToOne(mappedBy = "vehicleInformation",cascade = CascadeType.PERSIST)
    private Assignment assignment;

    @Column(name = "vin")
    private String vin;

    @Column(name = "year")
    private int year;

    @Column(name = "model")
    private String model;

    @Column(name = "make_in")
    private String makeIn;

    @Column(name = "license_part")
    private String licensePart;

    @Column(name = "license_state")
    private String licenseState;

    @Column(name = "license_expiration")
    private Date licenseExpiration;

    @Column(name = "odometer_value")
    private int odometerValue;

    public VehicleInformation(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleInformation that = (VehicleInformation) o;
        return vin == that.vin && year == that.year && odometerValue == that.odometerValue && Objects.equals(assignment, that.assignment) && Objects.equals(makeIn, that.makeIn) && Objects.equals(licensePart, that.licensePart) && Objects.equals(licenseState, that.licenseState) && Objects.equals(licenseExpiration, that.licenseExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignment, vin, year, makeIn, licensePart, licenseState, licenseExpiration, odometerValue);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getVehicleInformationId() {
        return vehicleInformationId;
    }

    public void setVehicleInformationId(int vehicleInformationId) {
        this.vehicleInformationId = vehicleInformationId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
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
}
