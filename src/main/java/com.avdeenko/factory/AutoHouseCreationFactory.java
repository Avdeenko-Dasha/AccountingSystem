package com.avdeenko.factory;

import com.avdeenko.model.model.Apartment;
import com.avdeenko.model.model.Floor;

import java.util.ArrayList;
import java.util.List;

public class AutoHouseCreationFactory extends AbstractHouseCreationFactory {
    private final Integer numberOfFloors;
    private final Integer numberOfApartments;
    private final List<Double> apartmentsSquare;

    public AutoHouseCreationFactory(Integer numberOfHouse) {
        super(numberOfHouse);
        this.numberOfFloors = (int) (2 + Math.random() * 10);
        this.numberOfApartments = (int) (1 + Math.random() * 5);
        this.apartmentsSquare = new ArrayList<>(numberOfApartments);
        for(int i = 0; i < numberOfApartments; ++i){
            apartmentsSquare.add(10 + Math.random() * 250);
        }
    }

    @Override
    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    @Override
    public Integer getNumberOfApartments() {
        return numberOfApartments;
    }

    @Override
    protected Floor createFloor(Integer numberOfHouse, Integer numberOfFloor) {
        return new Floor(numberOfHouse, numberOfFloor);
    }

    @Override
    protected Apartment createApartment(Integer numberOfHouse, Integer numberOfFloor, Integer numberOfApartment, Integer indexApartmentSquare) {
        Apartment apartment = new Apartment(numberOfHouse, numberOfFloor, numberOfApartment);
        apartment.setNumber(numberOfApartment);
        apartment.setSquareApartment(apartmentsSquare.get(indexApartmentSquare));
        apartment.setResidentCount((int) (Math.random() * 5));
        return apartment;
    }

}
