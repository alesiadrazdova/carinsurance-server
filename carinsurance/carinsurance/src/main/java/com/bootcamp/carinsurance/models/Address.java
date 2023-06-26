package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @ManyToOne
    @JoinColumn(name = "contact_id",referencedColumnName = "contact_id")
    private Contacts contact;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "note")
    private String note;

    @Column(name = "type_of_address")
    @Enumerated(EnumType.STRING)
    private TypeOfAddress typeOfAddress;

    public Address(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(contact, address.contact) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(zip, address.zip) && Objects.equals(note, address.note) && typeOfAddress == address.typeOfAddress;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact, city, state, zip, note, typeOfAddress);
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public Contacts getContact() {
        return contact;
    }

    public void setContact(Contacts contact) {
        this.contact = contact;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public TypeOfAddress getTypeOfAddress() {
        return typeOfAddress;
    }

    public void setTypeOfAddress(TypeOfAddress typeOfAddress) {
        this.typeOfAddress = typeOfAddress;
    }
}
