package com.avdeenko.factory;

import com.avdeenko.models.Apartment;
import com.avdeenko.models.Floor;

import java.util.ArrayList;
import java.util.List;

import static com.avdeenko.userinterface.Tools.enterNumDouble;
import static com.avdeenko.userinterface.Tools.enterNumInt;

public class YourselfHouseCreationFactory extends AbstractHouseCreationFactory{
    private final Integer floorNumber;
    private final Integer apartmentNumber;
    private final List<Double> apartmentsSquare;

    public YourselfHouseCreationFactory(Integer houseNumber) {
        super(houseNumber);
        System.out.print("Enter the number of floors - ");
        this.floorNumber = enterNumInt();
        System.out.print("Enter the number of apartments on the floor - ");
        this.apartmentNumber = enterNumInt();
        this.apartmentsSquare = new ArrayList<>(apartmentNumber);
        for(int i = 0; i < apartmentNumber; ++i){
            System.out.print("Enter the area of the " + (i+1) + " apartment on the floor - ");
            apartmentsSquare.add(enterNumDouble());
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
        System.out.print("Enter the number of tenants in " + numberApartment + " apartment - ");
        apartment.setResidentCount(enterNumInt());
        return apartment;
    }
}
