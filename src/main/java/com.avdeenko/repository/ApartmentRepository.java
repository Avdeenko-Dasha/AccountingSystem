package com.avdeenko.repository;

import com.avdeenko.database.DataBase;
import com.avdeenko.models.model.Apartment;
import com.avdeenko.models.collection.ApartmentCollection;
import com.google.gson.Gson;

import java.util.*;

public class ApartmentRepository {
    private final Gson gson;
    private static final String filePath = "D:\\Studies\\Proga\\Building\\src\\main\\resources\\apartments.json";

    public ApartmentRepository(Gson gson) {
        this.gson = gson;
    }

    public ApartmentRepository() {
        this.gson = new Gson();
    }

    public List<Apartment> getAll() {
        String body = DataBase.readFileAsString(filePath);
        ApartmentCollection collection = gson.fromJson(body, ApartmentCollection.class);
        return collection == null ? new ArrayList<>() : collection.getApartments();
    }

    public void addApartments(List<Apartment> inputApartments) {
        List<Apartment> apartments = getAll();
        apartments.addAll(inputApartments);
        DataBase.writeStringToFile(
            filePath, gson.toJson(new ApartmentCollection(apartments)));
    }

    public void addApartment(Apartment apartment) {
        List<Apartment> apartments = getAll();
        apartments.add(apartment);
        DataBase.writeStringToFile(
            filePath, gson.toJson(new ApartmentCollection(apartments)));
    }

    public void deleteApartment(Integer houseId, Integer floorId, Integer apartmentId) {
        List<Apartment> apartments = getAll();
        for (int i = 0; i < apartments.size(); ++i) {
            if (isSameApartment(apartments.get(i), houseId, floorId, apartmentId)) {
                apartments.remove(i);
                DataBase.writeStringToFile(
                    filePath, gson.toJson(new ApartmentCollection(apartments)));
                break;
            }
        }
    }

    public void deleteApartments(Integer houseId, Integer floorId){
        List<Apartment> apartments = getAll();
        for (int i = apartments.size(); i > 0; --i) {
            deleteApartment(houseId, floorId, i);
        }
        apartments.clear();
        apartments = getAll();
        DataBase.writeStringToFile(
            filePath, gson.toJson(new ApartmentCollection(apartments)));
    }

    public Apartment getApartment(Integer houseId, Integer floorId, Integer apartmentId) {
        List<Apartment> apartments = getAll();
        for (Apartment apartment : apartments) {
            if (isSameApartment(apartment, houseId, floorId, apartmentId)) {
                return apartment;
            }
        }
        return null;
    }

    public List<Apartment> getApartmentByHouseId(Integer houseId) {
        List<Apartment> apartments = getAll();
        List<Apartment> apartmentByHouse = new ArrayList<>();
        for (Apartment apartment : apartments) {
            if (Objects.equals(houseId, apartment.getNumberHouse())) {
                apartmentByHouse.add(apartment);
            }
        }
        return apartmentByHouse;
    }

    public List<Apartment> getApartmentByFloorId(Integer houseId, Integer floorId){
        List<Apartment> apartments = getAll();
        List<Apartment> apartmentByFloor = new ArrayList<>();
        for (Apartment apartment : apartments) {
            if (Objects.equals(houseId, apartment.getNumberHouse())
                && Objects.equals(floorId, apartment.getNumberFloor())) {
                apartmentByFloor.add(apartment);
            }
        }
        apartmentByFloor.sort(new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                return o1.getNumber().compareTo(o2.getNumber());
            }
        });
        return apartmentByFloor;
    }

    private boolean isSameApartment(Apartment apartment, Integer houseId, Integer floorId, Integer apartmentId) {
        return Objects.equals(houseId, apartment.getNumberHouse())
            && Objects.equals(floorId, apartment.getNumberFloor())
            && Objects.equals(apartmentId, apartment.getNumber());
    }
}
