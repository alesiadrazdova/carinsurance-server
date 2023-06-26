package com.bootcamp.carinsurance.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_of_parts")
public class TypeOfParts {

    @Id
    @Column(name = "type_of_parts_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeOfPartsId;

    @Column(name = "name_of_type")
    private String name;

    public TypeOfParts(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeOfParts that = (TypeOfParts) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getTypeOfPartsId() {
        return typeOfPartsId;
    }

    public void setTypeOfPartsId(int typeOfPartsId) {
        this.typeOfPartsId = typeOfPartsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
