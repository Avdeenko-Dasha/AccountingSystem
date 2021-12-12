package com.avdeenko.repository;

import com.avdeenko.database.DataBase;
import com.avdeenko.models.model.Floor;
import com.avdeenko.models.collection.FloorCollection;
import com.avdeenko.models.dao.FloorDAO;
import com.google.gson.Gson;

import java.util.*;

public class FloorRepository {
    private static final String filePath = "D:\\Studies\\Proga\\Building\\src\\main\\resources\\floors.json";
    private final Gson gson;
    private final ApartmentRepository apartmentRepository;

    public FloorRepository(Gson gson, ApartmentRepository apartmentRepository) {
        this.gson = gson;
        this.apartmentRepository = apartmentRepository;
    }

    public FloorRepository(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
        this.gson = new Gson();
    }

    public List<FloorDAO> getAll() {
        String body = DataBase.readFileAsString(filePath);
        FloorCollection collection = gson.fromJson(body, FloorCollection.class);
        return collection == null ? new ArrayList<>() : collection.getFloors();
    }

    public void addFloors(List<Floor> inputFloors) {
        List<FloorDAO> floors = getAll();
        for (Floor floor : inputFloors) {
            FloorDAO dao = new FloorDAO(floor);
            floors.add(dao);
        }
        DataBase.writeStringToFile(
            filePath, gson.toJson(new FloorCollection(floors)));
    }

    public void addFloor(Floor floor) {
        List<FloorDAO> floors = getAll();
        floors.add(new FloorDAO(floor));
        DataBase.writeStringToFile(
            filePath, gson.toJson(new FloorCollection(floors)));
    }

    public void deleteFloor(Integer houseId, Integer floorId) {
        List<FloorDAO> floors = getAll();
        for (int i = 0; i < floors.size(); ++i) {
            if (isSameFloor(floors.get(i), houseId, floorId)) {
                apartmentRepository.deleteApartments(houseId, floorId);
                floors.remove(i);
                DataBase.writeStringToFile(
                    filePath, gson.toJson(new FloorCollection(floors)));
                break;
            }
        }
    }

    public void deleteFloors(Integer houseId) {
        List<FloorDAO> floors = getAll();
        int numberOfFloors = 0;
        for (FloorDAO floor : floors) {
            if(Objects.equals(floor.getNumberHouse(), houseId)) {
                numberOfFloors++;
            }
        }
        for (int i = numberOfFloors; i > 0; --i) {
            deleteFloor(houseId, i);
        }
        floors = getAll();
        DataBase.writeStringToFile(
            filePath, gson.toJson(new FloorCollection(floors)));
    }

    public Floor getFloor(Integer houseId, Integer floorId) {
        List<FloorDAO> floors = getAll();
        for (FloorDAO dao : floors) {
            if(isSameFloor(dao, houseId, floorId)) {
                Floor floor = new Floor(houseId, floorId);
                floor.setApartments(apartmentRepository.getApartmentByFloorId(houseId, floorId));
                return floor;
            }
        }
        return null;
    }

    public List<Floor> getFloorByHouseId(Integer houseId) {
        List<FloorDAO> floors = getAll();
        List<Floor> floorByHouse = new ArrayList<>();
        Integer numberFloor = 0;
        for (FloorDAO dao : floors) {
            if(Objects.equals(dao.getNumberHouse(), houseId)) {
                numberFloor++;
                Floor floor = getFloor(houseId, numberFloor);
                floorByHouse.add(floor);
            }
        }
        return floorByHouse;
    }

    private boolean isSameFloor(FloorDAO floor, Integer houseId, Integer floorId) {
        return Objects.equals(houseId, floor.getNumberHouse())
            && Objects.equals(floorId, floor.getNumber());
    }
}
