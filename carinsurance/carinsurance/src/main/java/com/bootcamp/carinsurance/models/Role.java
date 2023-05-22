package com.bootcamp.carinsurance.models;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
@Entity
@Table(name = "role")
public class Role {
    public Role(){}

    public Role(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(people, role.people) && Objects.equals(nameOfRole, role.nameOfRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfRole);
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public String getNameOfRole() {
        return nameOfRole;
    }

    public void setNameOfRole(String nameOfRole) {
        this.nameOfRole = nameOfRole;
    }

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    @OneToMany
    private List<Person> people;

    @Column(name = "name_of_role",unique = true)
    @NotNull
    private String nameOfRole;
}
