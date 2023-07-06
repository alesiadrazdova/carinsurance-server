package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @Column(name = "assignment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User client;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicle_information_id",referencedColumnName = "vehicle_information_id")
    private VehicleInformation vehicleInformation;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_condition_id",referencedColumnName = "vehicle_condition_id")
    private VehicleCondition vehicleCondition;

    @OneToMany(mappedBy = "assignment",cascade = CascadeType.ALL)
    private List<Contact> contacts;

    @Column(name = "price")
    private BigDecimal totalPrice;

    @Column(name = "note")
    private String note;

    @Column(name = "date_of_incident")
    private Date dateOfIncident;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplement_id")
    private Supplement supplement;

    public Assignment(){}

    public void setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Supplement getSupplement() {
        return supplement;
    }

    public void setSupplement(Supplement supplement) {
        this.supplement = supplement;
    }

    public VehicleInformation getVehicleInformation() {
        return vehicleInformation;
    }

    public void setVehicleInformation(VehicleInformation vehicleInformation) {
        this.vehicleInformation = vehicleInformation;
    }

    public VehicleCondition getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VehicleCondition vehicleConditions) {
        this.vehicleCondition = vehicleConditions;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public long getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(long assignmentId) {
        this.assignmentId = assignmentId;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal price) {
        this.totalPrice = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDateOfIncident() {
        return dateOfIncident;
    }

    public void setDateOfIncident(Date dateOfIncident) {
        this.dateOfIncident = dateOfIncident;
    }
}
