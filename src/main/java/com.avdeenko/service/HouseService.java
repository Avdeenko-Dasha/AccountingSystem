package com.avdeenko.service;

import com.avdeenko.models.Apartment;
import com.avdeenko.models.House;
import com.avdeenko.repository.HouseRepository;

public class HouseService {
    public static House findHouse(int houseNumber) {
        return HouseRepository.getHouseByNumber(houseNumber).orElse(new House(0)) ;
    }

    private static Apartment findApartment(House house, int numberApartment) {
        for (int i = 0; i < house.getFloors().size(); ++i) {
            for (int j = 0; j < house.getFloors().get(i).getApartments().size(); ++j) {
                if (house.getFloors().get(i).getApartments().get(j).getNumberApartment() == numberApartment)
                    return house.getFloors().get(i).getApartments().get(j);
            }
        }
        return null;
    }

    public static int countApartments(House house) {
        return house.getFloors().size() * house.getFloors().get(0).getApartments().size();
    }

    public static int countTenantsHouse(House house) {
        int numberOfTenants = 0;
        for (int i = 0; i < house.getFloors().size(); ++i) {
            for(int j = 0; j < house.getFloors().get(i).getApartments().size(); ++j){
                numberOfTenants += house.getFloors().get(i).getApartments().get(j).getResidentCount();
            }
        }
        return numberOfTenants;
    }

    public static double calculateAreaHouse(House house) {
        double square = 0;
        for(int i = 0; i < house.getFloors().get(0).getApartments().size(); ++i){
            square += house.getFloors().get(0).getApartments().get(i).getSquareApartment();
        }
        return square * house.getFloors().size();
    }

    public static String compareHouses(House house1, House house2) {
        String str = "";
        double squareHouse1 = calculateAreaHouse(house1);
        double squareHouse2 = calculateAreaHouse(house2);
        int numResidents1 = countTenantsHouse(house1);
        int numResidents2 = countTenantsHouse(house2);
        if (squareHouse1 > squareHouse2) {
            str += "The area of the house " + house1.getHouseNumber() +
                    " is larger than the area of the house " + house2.getHouseNumber() + "\n";
        } else if (squareHouse1 < squareHouse2) {
            str +="The area of the house " + house1.getHouseNumber() +
                    " is less than the area of the house " + house2.getHouseNumber() + "\n";
        } else {
            str +="The area of the house " + house1.getHouseNumber() +
                    " is equal to the area of the house " + house2.getHouseNumber() + "\n";
        }
        str += "House " + house1.getHouseNumber() + " - " + String.format("%.2f", squareHouse1) +
                "    House " + house2.getHouseNumber() + " - " + String.format("%.2f", squareHouse2) + "\n";

        if (numResidents1 > numResidents2) {
            str += "The number of tenants in the house " + house1.getHouseNumber() +
                    " is greater than the number of tenants in the house " + house2.getHouseNumber() + "\n";
        } else if (numResidents1 < numResidents2) {
            str+="The number of tenants in the house " + house1.getHouseNumber() +
                    " is less than the number of tenants in the house " + house2.getHouseNumber()+ "\n";
        } else {
            str += "The number of tenants in the house " + house1.getHouseNumber() +
                    " is equal than the number of tenants in the house " + house2.getHouseNumber()+ "\n";
        }
        str += "House " + house1.getHouseNumber() + " - " + numResidents1 +
                "    House " + house2.getHouseNumber() + " - " + numResidents2;
        return str;
    }

    public static String compareApartments(House house, int numberApartment1, int numberApartment2) {
        Apartment apartment1 = findApartment(house, numberApartment1);
        Apartment apartment2 = findApartment(house, numberApartment2);
        String str = "";

        if (apartment1 != null && apartment2 != null) {
            if (apartment1.getSquareApartment() > apartment2.getSquareApartment()) {
                str += "The area of the apartment " + apartment1.getNumberApartment() +
                        " is larger than the area of the apartment " + apartment2.getNumberApartment() + "\n";
            } else if (apartment1.getSquareApartment() < apartment2.getSquareApartment()) {
                str += "The area of the apartment " + apartment1.getNumberApartment() +
                        " is less than the area of the apartment " + apartment2.getNumberApartment() + "\n";
            } else {
                str += "The area of the apartment " + apartment1.getNumberApartment() +
                        " is equal to the area of the apartment " + apartment2.getNumberApartment() + "\n";
            }
            str += "Apartment " + apartment1.getNumberApartment() + " - " + String.format("%.2f", apartment1.getSquareApartment()) +
                    "    Apartment " + apartment2.getNumberApartment() + " - " + String.format("%.2f", apartment2.getSquareApartment()) + "\n";

            if (apartment1.getResidentCount() > apartment2.getResidentCount()) {
                str += "The number of tenants in the apartment " + apartment1.getNumberApartment() +
                        " is greater than the number of tenants in the apartment " + apartment2.getNumberApartment() + "\n";
            } else if (apartment1.getResidentCount() < apartment2.getResidentCount()) {
                str += "The number of tenants in the apartment " + apartment1.getNumberApartment() +
                        " is less than the number of tenants in the apartment " + apartment2.getNumberApartment() + "\n";
            } else {
                str += "The number of tenants in the apartment " + apartment1.getNumberApartment() +
                        " is equal than the number of tenants in the apartment " + apartment2.getNumberApartment() + "\n";
            }
            str += "Apartment " + apartment1.getNumberApartment() + " - " + apartment1.getResidentCount() +
                    "    Apartment " + apartment2.getNumberApartment() + " - " + apartment2.getResidentCount();
        }
        return str;
    }
}
