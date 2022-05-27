package com.avdeenko.model.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apartment extends BaseModel {
    private Integer numberOfHouse;
    private Integer numberOfFloor;
    private Double squareApartment;
    private Integer residentCount;

    public Apartment(){
        super(null);
    }

    public Apartment(Integer numberOfHouse, Integer numberOfFloor, Integer numberOfApartment) {
        super(numberOfApartment);
        this.numberOfHouse = numberOfHouse;
        this.numberOfFloor = numberOfFloor;
    }

    public String toString() {
        return "Apartment - " + super.getNumber() +
            "   Square - " + String.format("%.2f", squareApartment) +
            "   Residents - " + residentCount + "<p>";
    }
}
