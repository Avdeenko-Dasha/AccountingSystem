package com.avdeenko.factory;

import com.avdeenko.models.model.Apartment;
import com.avdeenko.models.model.Floor;

import java.util.ArrayList;
import java.util.List;

public class AutoHouseCreationFactory extends AbstractHouseCreationFactory {
    private final Integer floorsNumber;
    private final Integer apartmentNumber;
    private final List<Double> apartmentsSquare;

    public AutoHouseCreationFactory(Integer houseNumber) {
        super(houseNumber);
        this.floorsNumber = (int) (2 + Math.random() * 10);
        this.apartmentNumber = (int) (1 + Math.random() * 5);
        this.apartmentsSquare = new ArrayList<>(apartmentNumber);
        for(int i = 0; i < apartmentNumber; ++i){
            apartmentsSquare.add(10 + Math.random() * 250);
        }
    }

    @Override
    protected Integer getFloorsNumber() { return floorsNumber; }

    @Override
    protected Integer getApartmentNumber() { return apartmentNumber; }

    @Override
    protected Floor createFloor(Integer houseNumber, Integer number) {
        return new Floor(houseNumber, number);
    }

    @Override
    protected Apartment createApartment(Integer houseNumber, Integer floorNumber, Integer numberApartment, Integer indexApartmentSquare) {
        Apartment apartment = new Apartment(houseNumber, floorNumber, numberApartment);
        apartment.setNumber(numberApartment);
        apartment.setSquareApartment(apartmentsSquare.get(indexApartmentSquare));
        apartment.setResidentCount((int) (Math.random() * 5));
        return apartment;
    }

}
