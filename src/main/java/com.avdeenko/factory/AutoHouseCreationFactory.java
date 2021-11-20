package com.avdeenko.factory;

import com.avdeenko.models.Apartment;
import com.avdeenko.models.Floor;

import java.util.ArrayList;
import java.util.List;

public class AutoHouseCreationFactory extends AbstractHouseCreationFactory {
    private final Integer floorNumber;
    private final Integer apartmentNumber;
    private final List<Double> apartmentsSquare;

    public AutoHouseCreationFactory(Integer houseNumber) {
        super(houseNumber);
        this.floorNumber = (int) (2 + Math.random() * 10);
        this.apartmentNumber = (int) (1 + Math.random() * 5);
        this.apartmentsSquare = new ArrayList<>(apartmentNumber);
        for(int i = 0; i < apartmentNumber; ++i){
            apartmentsSquare.add(10 + Math.random() * 250);
        }
    }

    @Override
    protected Integer getFloorNumber() { return floorNumber; }

    @Override
    protected Integer getApartmentNumber() { return apartmentNumber; }

    @Override
    protected Floor createFloor(Integer number) {
        Floor floor = new Floor();
        floor.setNumberFloor(number);
        return floor;
    }

    @Override
    protected Apartment createApartment(int numberApartment, int indexApartmentSquare) {
        Apartment apartment = new Apartment();
        apartment.setNumberApartment(numberApartment);
        apartment.setSquareApartment(apartmentsSquare.get(indexApartmentSquare));
        apartment.setResidentCount((int) (Math.random() * 5));
        return apartment;
    }

}
