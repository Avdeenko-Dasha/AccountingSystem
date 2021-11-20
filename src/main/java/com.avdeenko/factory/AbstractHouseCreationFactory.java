package com.avdeenko.factory;

import com.avdeenko.models.Apartment;
import com.avdeenko.models.Floor;
import com.avdeenko.models.House;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHouseCreationFactory {
    private final House house;

    public AbstractHouseCreationFactory(Integer houseNumber) {
        this.house = new House(houseNumber);
    }

    public House createHouse() {
        List<Floor> floors = makeFloors();
        house.setFloors(floors);
        return house;
    }

    private List<Floor> makeFloors() {
        Integer floorNumber = getFloorNumber();
        List<Floor> floors = new ArrayList<>(floorNumber);
        for (int i = 0; i < floorNumber; i++) {
            List<Apartment> apartments = makeApartments(i);
            Floor floor = createFloor(i + 1);
            floor.setApartments(apartments);
            floors.add(floor);
        }
        return floors;
    }

    protected abstract Integer getFloorNumber();

    protected abstract Floor createFloor(Integer number);

    private List<Apartment> makeApartments(Integer floorNumber) {
        Integer apartmentNumber = getApartmentNumber();
        List<Apartment> apartments = new ArrayList<>(apartmentNumber);
        if(floorNumber == 0)
            for(int i = 0; i < apartmentNumber; i++){
                apartments.add(createApartment(i+1, i));
            }
        else
            for(int i = 0; i < apartmentNumber; i++){
                apartments.add(createApartment(floorNumber*apartmentNumber + i + 1, i));
            }
        return apartments;
    }

    protected abstract Integer getApartmentNumber();

    protected abstract Apartment createApartment(int numberApartment, int indexApartmentSquare);
}
