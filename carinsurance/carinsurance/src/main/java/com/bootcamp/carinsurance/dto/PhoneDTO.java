package com.bootcamp.carinsurance.dto;

public class PhoneDTO {
    private String number;
    private String typeOfPhone;
    public PhoneDTO(){}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTypeOfPhone() {
        return typeOfPhone;
    }

    public void setTypeOfPhone(String typeOfPhone) {
        this.typeOfPhone = typeOfPhone;
    }

    public PhoneDTO(String number, String typeOfPhone) {
        this.number = number;
        this.typeOfPhone = typeOfPhone;
    }
}
