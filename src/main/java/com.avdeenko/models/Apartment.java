package com.avdeenko.models;

import lombok.Data;

@Data
public class Apartment {
    /**
     * Variable that stores the apartment number
     */
    private int numberApartment;
    /**
     * Variable that stores the square of the apartment
     */
    private double squareApartment;
    /**
     * Variable that stores the number of residents in the apartment
     */
    private int residentCount;

    public String toString() {
        return "Apartment - " + numberApartment + " Square - " + String.format("%.2f", squareApartment) + " Residents - " + residentCount;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Apartment)) return false;
        if(squareApartment == 0 || ((Apartment) obj).squareApartment == 0) return false;
        return squareApartment == (((Apartment) obj).squareApartment) && residentCount == (((Apartment) obj).residentCount);
    }
}
