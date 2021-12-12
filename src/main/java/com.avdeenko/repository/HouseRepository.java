package com.avdeenko.repository;

import com.avdeenko.database.DataBase;
import com.avdeenko.models.model.House;
import com.avdeenko.models.collection.HouseCollection;
import com.avdeenko.models.dao.HouseDAO;
import com.google.gson.Gson;

import java.util.*;

public class HouseRepository {
    private static final String filePath = "D:\\Studies\\Proga\\Building\\src\\main\\resources\\houses.json";
    private final Gson gson;
    private final FloorRepository floorRepository;

    public HouseRepository(Gson gson, FloorRepository floorRepository) {
        this.gson = gson;
        this.floorRepository = floorRepository;
    }

    public HouseRepository() {
        this.gson = new Gson();
        ApartmentRepository apartmentRepository = new ApartmentRepository();
        this.floorRepository = new FloorRepository(apartmentRepository);
    }

    public Set<HouseDAO> getAll() {
        String body = DataBase.readFileAsString(filePath);
        HouseCollection collection = gson.fromJson(body, HouseCollection.class);
        return collection == null ? new HashSet<>() : collection.getHouses();
    }

    public void addHouses(List<House> inputHouses) {
        Set<HouseDAO> houses = getAll();
        for(House house : inputHouses){
            houses.add(new HouseDAO(house));
        }
        DataBase.writeStringToFile(
            filePath, gson.toJson(new HouseCollection(houses)));
    }

    public void addHouse(House house) {
        Set<HouseDAO> houses = getAll();
        houses.add(new HouseDAO(house));
        DataBase.writeStringToFile(
            filePath, gson.toJson(new HouseCollection(houses)));
    }

    public void deleteHouse(Integer houseId) {
        Set<HouseDAO> houses = getAll();
        for (HouseDAO house : houses) {
            if (isSameHouse(house, houseId)) {
                floorRepository.deleteFloors(houseId);
                houses.remove(house);
                DataBase.writeStringToFile(
                    filePath, gson.toJson(new HouseCollection(houses)));
                break;
            }
        }
    }

    public House getHouse(Integer houseId) {
        Set<HouseDAO> houses = getAll();
        for (HouseDAO dao : houses) {
            if (isSameHouse(dao, houseId)) {
                House house = new House(houseId);
                house.setFloors(floorRepository.getFloorByHouseId(houseId));
                return house;
            }
        }
        return null;
    }

    public List<House> getHouses() {
        Set<HouseDAO> houses = getAll();
        List<House> allHouses = new ArrayList<>();
        for (HouseDAO dao : houses) {
            House house = getHouse(dao.getNumber());
            if (house != null) {
                allHouses.add(house);
            }
        }
        return allHouses;
    }

    private boolean isSameHouse(HouseDAO house, Integer houseId) {
        return Objects.equals(houseId, house.getNumber());
    }

    public void printHouseNumbers(){
        System.out.println("Number of existing houses:");
        List<House> houses = getHouses();
        for(House house : houses){
            System.out.print(house.getNumber() + " ");
        }
        System.out.println();
    }

    public boolean isRepositoryNonNull() {
        List<House> houses = getHouses();
        return !houses.isEmpty();
    }

    public boolean isRepositoryHaveTwoHouses() {
        List<House> houses = getHouses();
        return houses.size() < 2;
    }
}
