package com.avdeenko.factory;

import com.avdeenko.model.model.Apartment;
import com.avdeenko.model.model.Floor;

import java.util.List;

public class ManualHouseCreationFactory extends AbstractHouseCreationFactory{
    private final Integer numberOfFloors;
    private final Integer numberOfApartments;
    private final List<Double> apartmentsSquare;
    private final List<Integer> apartmentsResidents;

    public ManualHouseCreationFactory(Integer numberOfHouse, Integer numberOfFloors, Integer numberOfApartments,
                                      List<Double> apartmentsSquare, List<Integer> apartmentsResidents) {
        super(numberOfHouse);
        this.numberOfFloors = numberOfFloors;
        this.numberOfApartments = numberOfApartments;
        this.apartmentsSquare = apartmentsSquare;
        this.apartmentsResidents = apartmentsResidents;
    }

    @Override
    protected Integer getNumberOfFloors() { return numberOfFloors; }

    @Override
    protected Integer getNumberOfApartments() { return numberOfApartments; }

    @Override
    protected Floor createFloor(Integer numberOfHouse, Integer numberOfFloor) {
        return new Floor(numberOfHouse, numberOfFloor);
    }

    @Override
    protected Apartment createApartment(Integer numberOfHouse, Integer numberOfFloor,  Integer numberOfApartment, Integer indexApartmentSquare) {
        Apartment apartment = new Apartment(numberOfHouse, numberOfFloor, numberOfApartment);
        apartment.setSquareApartment(apartmentsSquare.get(indexApartmentSquare));
        apartment.setResidentCount(apartmentsResidents.get(numberOfApartment-1));
        return apartment;
    }
}
