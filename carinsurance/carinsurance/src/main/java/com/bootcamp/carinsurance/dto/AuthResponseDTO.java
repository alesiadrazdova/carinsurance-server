package com.bootcamp.carinsurance.dto;

public class AuthResponseDTO {
    private  String message;
    private  String role;
    private String firstname;
    private String lastName;
    AuthResponseDTO(){}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AuthResponseDTO(String message, String role, String firstname, String lastName) {
        this.message = message;
        this.role = role;
        this.firstname=firstname;
        this.lastName=lastName;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
