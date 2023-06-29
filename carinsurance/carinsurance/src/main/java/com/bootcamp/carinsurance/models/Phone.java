package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "phone")
public class Phone {

    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phoneId;

    @ManyToOne()
    @JoinColumn(name = "contact_id",referencedColumnName = "contact_id")
    private Contact contact;

    @Column(name = "number")
    private String number;

    @Column(name = "type_of_phone")
    @Enumerated(EnumType.STRING)
    private TypeOfPhone typeOfPhone;

    public Phone(){}

    public Phone( String number, TypeOfPhone typeOfPhone) {
        this.number = number;
        this.typeOfPhone = typeOfPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Objects.equals(contact, phone.contact) && Objects.equals(number, phone.number) && typeOfPhone == phone.typeOfPhone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact, number, typeOfPhone);
    }

    public int getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(int phoneId) {
        this.phoneId = phoneId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TypeOfPhone getTypeOfPhone() {
        return typeOfPhone;
    }

    public void setTypeOfPhone(TypeOfPhone typeOfPhone) {
        this.typeOfPhone = typeOfPhone;
    }
}
