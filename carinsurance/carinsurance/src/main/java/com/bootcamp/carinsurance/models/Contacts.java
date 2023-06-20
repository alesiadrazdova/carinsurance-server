package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "contacts")
public class Contacts {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @ManyToOne
    @JoinColumn(name = "assignment_id",referencedColumnName = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "type_of_contact_id",referencedColumnName = "type_of_contact_id")
    private TypeOfContact typeOfContact;

    @OneToMany(mappedBy = "contact")
    private List<Phone> phones;

    @OneToMany(mappedBy = "contact")
    private List<Address> addresses;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "note")
    private String note;

    public Contacts(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(assignment, contacts.assignment) && Objects.equals(typeOfContact, contacts.typeOfContact) && Objects.equals(firstname, contacts.firstname) && Objects.equals(lastname, contacts.lastname) && Objects.equals(email, contacts.email) && Objects.equals(note, contacts.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assignment, typeOfContact, firstname, lastname, email, note);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int vehicleInformationId) {
        this.contactId = vehicleInformationId;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public TypeOfContact getTypeOfContact() {
        return typeOfContact;
    }

    public void setTypeOfContact(TypeOfContact typeOfContact) {
        this.typeOfContact = typeOfContact;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
