package com.avdeenko.factory;

import com.avdeenko.model.model.Apartment;
import com.avdeenko.model.model.Floor;
import com.avdeenko.model.model.House;
import com.avdeenko.repository.HouseRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHouseCreationFactory {
    private final House house;
    private final HouseRepositoryImpl houseRepository;

    public AbstractHouseCreationFactory(Integer numberOfHouse) {
        this.house = new House(numberOfHouse);
        this.houseRepository = HouseRepositoryImpl.getHouseRepository();
    }

    public House createHouse() {
        List<Floor> floors = makeFloors();
        house.setFloors(floors);
        flushToDB();
        return house;
    }

    private void flushToDB() {
        houseRepository.creat(house);
    }

    private List<Floor> makeFloors() {
        Integer numberOfHouse = house.getNumber();
        Integer numberOfFloors = getNumberOfFloors();
        List<Floor> floors = new ArrayList<>(numberOfFloors);
        for (int i = 0; i < numberOfFloors; i++) {
            List<Apartment> apartments = makeApartments(numberOfHouse, i);
            Floor floor = createFloor(numberOfHouse, i + 1);
            floor.setApartments(apartments);
            floors.add(floor);
        }
        return floors;
    }

    protected abstract Integer getNumberOfFloors();

    protected abstract Floor createFloor(Integer numberOfHouse, Integer numberOfFloor);

    private List<Apartment> makeApartments(Integer numberOfHouse, Integer numberOfFloor) {
        Integer numberOfApartments = getNumberOfApartments();
        List<Apartment> apartments = new ArrayList<>(numberOfApartments);
        if (numberOfFloor == 0)
            for (int i = 0; i < numberOfApartments; i++) {
                apartments.add(createApartment(numberOfHouse, numberOfFloor + 1, i + 1, i));
            }
        else
            for (int i = 0; i < numberOfApartments; i++) {
                apartments.add(createApartment(numberOfHouse, numberOfFloor + 1, numberOfFloor * numberOfApartments + i + 1, i));
            }
        return apartments;
    }

    protected abstract Integer getNumberOfApartments();

    protected abstract Apartment createApartment(Integer numberOfHouse, Integer numberOfFloor, Integer numberOfApartment, Integer indexApartmentSquare);
}
