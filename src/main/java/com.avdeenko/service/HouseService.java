package com.avdeenko.service;

import com.avdeenko.model.model.Apartment;
import com.avdeenko.model.model.House;
import com.avdeenko.repository.ApartmentRepositoryImpl;
import com.avdeenko.repository.HouseRepositoryImpl;

import java.util.List;

public class HouseService {
    private final HouseRepositoryImpl houseRepository = HouseRepositoryImpl.getHouseRepository();
    private static HouseService houseService;

    private HouseService() {
    }

    public static HouseService getHouseService() {
        if (houseService == null) {
            houseService = new HouseService();
        }
        return houseService;
    }

    public House findHouse(Integer numberOfHouse) {
        return houseRepository.readByKey(numberOfHouse).get();
    }

    private Apartment findApartment(Integer numberOfHouse, int numberOfApartment) {
        ApartmentRepositoryImpl apartmentRepository = ApartmentRepositoryImpl.getApartmentRepository();
        return apartmentRepository.readByKey(numberOfApartment, houseRepository.getID(numberOfHouse)).get();
    }

    public Integer countTenantsInHouse(Integer numberOfHouse) {
        return houseRepository.countTenantsInHouse(numberOfHouse);
    }

    public Double calculateAreaOfHouse(Integer numberOfHouse){
        return houseRepository.calculateAreaOfHouse(numberOfHouse);
    }

    public void deleteHouse(Integer numberOfHouse){
        houseRepository.deleteByKey(houseRepository.getID(numberOfHouse));
    }

    public Integer countApartments(Integer numberOfHouse){
        return houseRepository.countApartments(numberOfHouse);
    }

    public List<Integer> getHouseNumbers(){
        return houseRepository.getHouseNumbers();
    }

    public int checkingHouseNumber(int numberOfHouse){
        while(!houseRepository.getHouseNumbers().contains(numberOfHouse)){
            return -1;
        }
        return numberOfHouse;
    }

    public String compareHouses(Integer numberOfHouse1, Integer numberOfHouse2) {
        String str = "";
        double squareHouse1 = calculateAreaOfHouse(numberOfHouse1);
        double squareHouse2 = calculateAreaOfHouse(numberOfHouse2);
        int numResidents1 = countTenantsInHouse(numberOfHouse1);
        int numResidents2 = countTenantsInHouse(numberOfHouse2);
        if (squareHouse1 > squareHouse2) {
            str += "The area of the house " + numberOfHouse1 +
                " is larger than the area of the house " + numberOfHouse2 + "<p>";
        } else if (squareHouse1 < squareHouse2) {
            str += "The area of the house " + numberOfHouse1 +
                " is less than the area of the house " + numberOfHouse2 + "<p>";
        } else {
            str += "The area of the house " + numberOfHouse1 +
                " is equal to the area of the house " + numberOfHouse2 + "<p>";
        }
        str += "House " + numberOfHouse1 + " - " + String.format("%.2f", squareHouse1) +
            "    House " + numberOfHouse2 + " - " + String.format("%.2f", squareHouse2) + "<p>";

        if (numResidents1 > numResidents2) {
            str += "The number of tenants in the house " + numberOfHouse1 +
                " is greater than the number of tenants in the house " + numberOfHouse2 + "<p>";
        } else if (numResidents1 < numResidents2) {
            str += "The number of tenants in the house " + numberOfHouse1 +
                " is less than the number of tenants in the house " + numberOfHouse2 + "<p>";
        } else {
            str += "The number of tenants in the house " + numberOfHouse1 +
                " is equal than the number of tenants in the house " + numberOfHouse2 + "<p>";
        }
        str += "House " + numberOfHouse1 + " - " + numResidents1 +
            "    House " + numberOfHouse2 + " - " + numResidents2 + "<p>";
        return str;
    }

    public String compareApartments(Integer numberOfHouse1,Integer numberOfHouse2, Integer numberOfApartment1, Integer numberOfApartment2) {
        Apartment apartment1 = findApartment(numberOfHouse1, numberOfApartment1);
        Apartment apartment2 = findApartment(numberOfHouse2, numberOfApartment2);
        String str = "";

        if (apartment1.getSquareApartment() > apartment2.getSquareApartment()) {
            str += "The area of the apartment " + apartment1.getNumber() +
                " is larger than the area of the apartment " + numberOfApartment2 + "\n";
        } else if (apartment1.getSquareApartment() < apartment2.getSquareApartment()) {
            str += "The area of the apartment " + numberOfApartment1 +
                " is less than the area of the apartment " + numberOfApartment2 + "\n";
        } else {
            str += "The area of the apartment " + numberOfApartment1 +
                " is equal to the area of the apartment " + numberOfApartment2 + "\n";
        }
        str += "Apartment " + numberOfApartment1 + " - " + String.format("%.2f", apartment1.getSquareApartment()) +
            "    Apartment " + numberOfApartment2 + " - " + String.format("%.2f", apartment2.getSquareApartment()) + "\n";

        if (apartment1.getResidentCount() > apartment2.getResidentCount()) {
            str += "The number of tenants in the apartment " + numberOfApartment1 +
                " is greater than the number of tenants in the apartment " + numberOfApartment2 + "\n";
        } else if (apartment1.getResidentCount() < apartment2.getResidentCount()) {
            str += "The number of tenants in the apartment " + numberOfApartment1 +
                " is less than the number of tenants in the apartment " + numberOfApartment2 + "\n";
        } else {
            str += "The number of tenants in the apartment " + numberOfApartment1 +
                " is equal than the number of tenants in the apartment " + numberOfApartment2 + "\n";
        }
        str += "Apartment " + numberOfApartment1 + " - " + apartment1.getResidentCount() +
            "    Apartment " + numberOfApartment2 + " - " + apartment2.getResidentCount() + "\n";

        ApartmentRepositoryImpl apartmentRepository = ApartmentRepositoryImpl.getApartmentRepository();

        if(apartmentRepository.checkNormOfLivingSpace(numberOfApartment1,houseRepository.getID(numberOfHouse1))){
            str += "The apartment " + numberOfApartment1 + " meets the standards of living space\n";
        }else{
            str+="The apartment " + numberOfApartment1 + "does not meets the standards of living space\n";
        }

        if(apartmentRepository.checkNormOfLivingSpace(numberOfApartment2,houseRepository.getID(numberOfHouse2))){
            str += "The apartment " + numberOfApartment2 + " meets the standards of living space";
        }else{
            str += "The apartment " + numberOfApartment2 + " does not meets the standards of living space";
        }
        return str;
    }
}
