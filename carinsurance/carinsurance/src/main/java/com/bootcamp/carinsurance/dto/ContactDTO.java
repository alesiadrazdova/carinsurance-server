package com.bootcamp.carinsurance.dto;

import java.util.List;

public class ContactDTO {
    private TypeOfContactDTO typeOfContact;

    private String firstname;

    private String lastname;

    private String email;

    private List<PhoneDTO> phones;

    private List<AddressDTO> addresses;

    public ContactDTO(){}

    public TypeOfContactDTO getTypeOfContact() {
        return typeOfContact;
    }

    public void setTypeOfContact(TypeOfContactDTO typeOfContact) {
        this.typeOfContact = typeOfContact;
    }


    public ContactDTO(TypeOfContactDTO typeOfContact, String firstname, String lastname, String email, List<PhoneDTO> phones, List<AddressDTO> addresses) {
        this.typeOfContact = typeOfContact;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phones = phones;
        this.addresses = addresses;
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

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
}
