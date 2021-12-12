package com.avdeenko.factory;

import com.avdeenko.models.model.Apartment;
import com.avdeenko.models.model.Floor;

import java.util.ArrayList;
import java.util.List;

import static com.avdeenko.userInterface.UserInterface.enterNumDouble;
import static com.avdeenko.userInterface.UserInterface.enterNumInt;

public class ManualHouseCreationFactory extends AbstractHouseCreationFactory{
    private final Integer floorsNumber;
    private final Integer apartmentNumber;
    private final List<Double> apartmentsSquare;

    public ManualHouseCreationFactory(Integer houseNumber) {
        super(houseNumber);
        System.out.print("Enter the number of floors - ");
        this.floorsNumber = enterNumInt();
        System.out.print("Enter the number of apartments on the floor - ");
        this.apartmentNumber = enterNumInt();
        this.apartmentsSquare = new ArrayList<>(apartmentNumber);
        for(int i = 0; i < apartmentNumber; ++i){
            System.out.print("Enter the area of the " + (i+1) + " apartment on the floor - ");
            apartmentsSquare.add(enterNumDouble());
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
    protected Apartment createApartment(Integer houseNumber, Integer floorNumber,  Integer numberApartment, Integer indexApartmentSquare) {
        Apartment apartment = new Apartment(houseNumber, floorNumber, numberApartment);
        apartment.setSquareApartment(apartmentsSquare.get(indexApartmentSquare));
        System.out.print("Enter the number of tenants in " + numberApartment + " apartment - ");
        apartment.setResidentCount(enterNumInt());
        return apartment;
    }
}
