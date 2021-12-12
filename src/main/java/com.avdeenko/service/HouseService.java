package com.avdeenko.service;

import com.avdeenko.models.model.Apartment;
import com.avdeenko.models.model.House;
import com.avdeenko.repository.HouseRepository;

public class HouseService {
    private static HouseService houseService;

    private HouseService() {
    }

    public static HouseService getHouseService() {
        if (houseService == null) {
            houseService = new HouseService();
        }
        return houseService;
    }

    public House findHouse(int houseNumber) {
        HouseRepository houseRepository = new HouseRepository();
        return houseRepository.getHouse(houseNumber);
    }

    private Apartment findApartment(House house, int numberApartment) {
        for (int i = 0; i < house.getFloors().size(); ++i) {
            for (int j = 0; j < house.getFloors().get(i).getApartments().size(); ++j) {
                if (house.getFloors().get(i).getApartments().get(j).getNumber() == numberApartment)
                    return house.getFloors().get(i).getApartments().get(j);
            }
        }
        return null;
    }

    public int countApartments(House house) {
        return house.getFloors().size() * house.getFloors().get(0).getApartments().size();
    }

    public int countTenantsHouse(House house) {
        int numberOfTenants = 0;
        for (int i = 0; i < house.getFloors().size(); ++i) {
            for (int j = 0; j < house.getFloors().get(i).getApartments().size(); ++j) {
                numberOfTenants += house.getFloors().get(i).getApartments().get(j).getResidentCount();
            }
        }
        return numberOfTenants;
    }

    public double calculateAreaHouse(House house) {
        double square = 0;
        for (int i = 0; i < house.getFloors().get(0).getApartments().size(); ++i) {
            square += house.getFloors().get(0).getApartments().get(i).getSquareApartment();
        }
        return square * house.getFloors().size();
    }

    public String compareHouses(House house1, House house2) {
        String str = "";
        double squareHouse1 = calculateAreaHouse(house1);
        double squareHouse2 = calculateAreaHouse(house2);
        int numResidents1 = countTenantsHouse(house1);
        int numResidents2 = countTenantsHouse(house2);
        if (squareHouse1 > squareHouse2) {
            str += "The area of the house " + house1.getNumber() +
                " is larger than the area of the house " + house2.getNumber() + "\n";
        } else if (squareHouse1 < squareHouse2) {
            str += "The area of the house " + house1.getNumber() +
                " is less than the area of the house " + house2.getNumber() + "\n";
        } else {
            str += "The area of the house " + house1.getNumber() +
                " is equal to the area of the house " + house2.getNumber() + "\n";
        }
        str += "House " + house1.getNumber() + " - " + String.format("%.2f", squareHouse1) +
            "    House " + house2.getNumber() + " - " + String.format("%.2f", squareHouse2) + "\n";

        if (numResidents1 > numResidents2) {
            str += "The number of tenants in the house " + house1.getNumber() +
                " is greater than the number of tenants in the house " + house2.getNumber() + "\n";
        } else if (numResidents1 < numResidents2) {
            str += "The number of tenants in the house " + house1.getNumber() +
                " is less than the number of tenants in the house " + house2.getNumber() + "\n";
        } else {
            str += "The number of tenants in the house " + house1.getNumber() +
                " is equal than the number of tenants in the house " + house2.getNumber() + "\n";
        }
        str += "House " + house1.getNumber() + " - " + numResidents1 +
            "    House " + house2.getNumber() + " - " + numResidents2;
        return str;
    }

    public String compareApartments(House house, int numberApartment1, int numberApartment2) {
        Apartment apartment1 = findApartment(house, numberApartment1);
        Apartment apartment2 = findApartment(house, numberApartment2);
        String str = "";

        if (apartment1 != null && apartment2 != null) {
            if (apartment1.getSquareApartment() > apartment2.getSquareApartment()) {
                str += "The area of the apartment " + apartment1.getNumber() +
                    " is larger than the area of the apartment " + apartment2.getNumber() + "\n";
            } else if (apartment1.getSquareApartment() < apartment2.getSquareApartment()) {
                str += "The area of the apartment " + apartment1.getNumber() +
                    " is less than the area of the apartment " + apartment2.getNumber() + "\n";
            } else {
                str += "The area of the apartment " + apartment1.getNumber() +
                    " is equal to the area of the apartment " + apartment2.getNumber() + "\n";
            }
            str += "Apartment " + apartment1.getNumber() + " - " + String.format("%.2f", apartment1.getSquareApartment()) +
                "    Apartment " + apartment2.getNumber() + " - " + String.format("%.2f", apartment2.getSquareApartment()) + "\n";

            if (apartment1.getResidentCount() > apartment2.getResidentCount()) {
                str += "The number of tenants in the apartment " + apartment1.getNumber() +
                    " is greater than the number of tenants in the apartment " + apartment2.getNumber() + "\n";
            } else if (apartment1.getResidentCount() < apartment2.getResidentCount()) {
                str += "The number of tenants in the apartment " + apartment1.getNumber() +
                    " is less than the number of tenants in the apartment " + apartment2.getNumber() + "\n";
            } else {
                str += "The number of tenants in the apartment " + apartment1.getNumber() +
                    " is equal than the number of tenants in the apartment " + apartment2.getNumber() + "\n";
            }
            str += "Apartment " + apartment1.getNumber() + " - " + apartment1.getResidentCount() +
                "    Apartment " + apartment2.getNumber() + " - " + apartment2.getResidentCount();
        }
        return str;
    }
}
