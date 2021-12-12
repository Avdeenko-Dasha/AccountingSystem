package com.avdeenko.models.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apartment extends BaseModel {
    private Integer numberHouse;
    private Integer numberFloor;
    private Double squareApartment;
    private Integer residentCount;

    public Apartment(Integer numberHouse, Integer numberFloor, Integer numberApartment) {
        super(numberApartment);
        this.numberHouse = numberHouse;
        this.numberFloor = numberFloor;
    }

    public String toString() {
        return System.lineSeparator() + "Apartment - " + super.getNumber() +
            " Square - " + String.format("%.2f", squareApartment) +
            " Residents - " + residentCount;
    }
}
