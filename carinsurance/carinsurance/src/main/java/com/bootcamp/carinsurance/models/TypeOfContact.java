package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_of_contact")
public class TypeOfContact {
    @Id
    @Column(name = "type_of_contact_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeOfContactId;

    @Column(name = "name_of_contact")
    private String name;

    public TypeOfContact(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfContact that = (TypeOfContact) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getTypeOfContactId() {
        return typeOfContactId;
    }

    public void setTypeOfContactId(int typeOfContactId) {
        this.typeOfContactId = typeOfContactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
