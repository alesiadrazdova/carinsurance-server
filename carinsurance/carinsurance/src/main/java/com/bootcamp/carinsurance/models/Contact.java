package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @Column(name = "contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignment_id",referencedColumnName = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "type_of_contact_id",referencedColumnName = "type_of_contact_id")
    private TypeOfContact typeOfContact;

    @OneToMany(mappedBy = "contact",cascade = CascadeType.ALL)
    private List<Phone> phones;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    public Contact(){}

    public Contact(Assignment assignment,
                   TypeOfContact typeOfContact,
                   List<Phone> phones,
                   List<Address> addresses,
                   String firstname,
                   String lastname,
                   String email) {
        this.assignment = assignment;
        this.typeOfContact = typeOfContact;
        this.phones = phones;
        this.addresses = addresses;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
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
}
