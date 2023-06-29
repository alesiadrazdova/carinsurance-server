package com.bootcamp.carinsurance.dto;

import com.bootcamp.carinsurance.models.Status;
import com.bootcamp.carinsurance.models.Supplement;
import com.bootcamp.carinsurance.models.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class AssignmentDTO {

    private Status status;

    private User client;

    private VehicleInformationDTO vehicleInformation;

    private VehicleConditionDTO vehicleCondition;

    private List<ContactDTO> contacts;

    private BigDecimal totalPrice;

    private String assignmentNote;

    private Date dateOfIncident;

    private List<Supplement> supplements;


    public AssignmentDTO(){}


    public AssignmentDTO(VehicleInformationDTO vehicleInformation,
                         VehicleConditionDTO vehicleCondition,
                         List<ContactDTO> contacts,
                         String assignmentNote,
                         Date dateOfIncident) {

        this.vehicleInformation = vehicleInformation;
        this.vehicleCondition = vehicleCondition;
        this.contacts = contacts;
        this.assignmentNote = assignmentNote;
        this.dateOfIncident = dateOfIncident;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public List<ContactDTO> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactDTO> contacts) {
        this.contacts = contacts;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAssignmentNote() {
        return assignmentNote;
    }

    public void setAssignmentNote(String assignmentNote) {
        this.assignmentNote = assignmentNote;
    }

    public Date getDateOfIncident() {
        return dateOfIncident;
    }

    public void setDateOfIncident(Date dateOfIncident) {
        this.dateOfIncident = dateOfIncident;
    }

    public VehicleInformationDTO getVehicleInformation() {
        return vehicleInformation;
    }

    public void setVehicleInformation(VehicleInformationDTO vehicleInformation) {
        this.vehicleInformation = vehicleInformation;
    }

    public VehicleConditionDTO getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VehicleConditionDTO vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public List<Supplement> getSupplements() {
        return supplements;
    }

    public void setSupplements(List<Supplement> supplements) {
        this.supplements = supplements;
    }
}
