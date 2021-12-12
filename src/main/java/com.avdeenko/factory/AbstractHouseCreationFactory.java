package com.avdeenko.factory;

import com.avdeenko.models.model.Apartment;
import com.avdeenko.models.model.Floor;
import com.avdeenko.models.model.House;
import com.avdeenko.repository.ApartmentRepository;
import com.avdeenko.repository.FloorRepository;
import com.avdeenko.repository.HouseRepository;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHouseCreationFactory {
    private final House house;
    private final ApartmentRepository apartmentRepository;
    private final FloorRepository floorRepository;
    private final HouseRepository houseRepository;

    public AbstractHouseCreationFactory(Integer houseNumber) {
        this.house = new House(houseNumber);
        this.apartmentRepository = new ApartmentRepository();
        this.floorRepository = new FloorRepository(apartmentRepository);
        this.houseRepository = new HouseRepository();
    }

    public House createHouse() {
        List<Floor> floors = makeFloors();
        house.setFloors(floors);
        flushToDB();
        return house;
    }

    private void flushToDB() {
        List<Apartment> apartments = new ArrayList<>();
        List<Floor> floors = new ArrayList<>();
        for (Floor floor : house.getFloors()) {
            floors.add(floor);
            apartments.addAll(floor.getApartments());
        }
        apartmentRepository.addApartments(apartments);
        floorRepository.addFloors(floors);
        houseRepository.addHouse(house);
        // TODO: FloorRepository
        // TODO: HouseRepository
    }

    private List<Floor> makeFloors() {
        Integer numberHouse = house.getNumber();
        Integer floorNumber = getFloorsNumber();
        List<Floor> floors = new ArrayList<>(floorNumber);
        for (int i = 0; i < floorNumber; i++) {
            List<Apartment> apartments = makeApartments(numberHouse, i);
            Floor floor = createFloor(numberHouse, i + 1);
            floor.setApartments(apartments);
            floors.add(floor);
        }
        return floors;
    }

    protected abstract Integer getFloorsNumber();

    protected abstract Floor createFloor(Integer houseNumber, Integer number);

    private List<Apartment> makeApartments(Integer houseNumber, Integer floorNumber) {
        Integer apartmentNumber = getApartmentNumber();
        List<Apartment> apartments = new ArrayList<>(apartmentNumber);
        if (floorNumber == 0)
            for (int i = 0; i < apartmentNumber; i++) {
                apartments.add(createApartment(houseNumber, floorNumber + 1, i + 1, i));
            }
        else
            for (int i = 0; i < apartmentNumber; i++) {
                apartments.add(createApartment(houseNumber, floorNumber + 1, floorNumber * apartmentNumber + i + 1, i));
            }
        return apartments;
    }

    protected abstract Integer getApartmentNumber();

    protected abstract Apartment createApartment(Integer houseNumber, Integer floorNumber, Integer numberApartment, Integer indexApartmentSquare);
}
