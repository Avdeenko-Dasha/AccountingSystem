package com.avdeenko.repository;

import com.avdeenko.models.House;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HouseRepository {
    private static final Map<Integer, House> houses = new HashMap<>();

    public static Map<Integer, House> getHouses() {
        return houses;
    }

    public static void addHouse(final House house) {
        houses.put(house.getHouseNumber(), house);
    }

    public static void printHouseNumbers(){
        System.out.println("Number of existing houses:");
        for(Map.Entry<Integer, House> house : houses.entrySet()){
            System.out.print(house.getKey() + " ");
        }
        System.out.println();
    }

    public static Optional<House> getHouseByNumber(final int houseNumber) {
        return Optional.ofNullable(houses.get(houseNumber));
    }

    public static void removeHouse(final int houseNumber) {
        houses.remove(houseNumber);
    }

    public static boolean isRepositoryNull() {
        return houses.isEmpty();
    }

    public static boolean isRepositoryHaveTwoHouses() {
        return houses.size() < 2;
    }
}
