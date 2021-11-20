package com.avdeenko.userInterface;

import com.avdeenko.factory.YourselfHouseCreationFactory;
import com.avdeenko.factory.AutoHouseCreationFactory;
import com.avdeenko.models.House;
import com.avdeenko.repository.HouseRepository;
import com.avdeenko.service.HouseService;

import java.util.Map;
import java.util.Scanner;

public interface Tools {
    static void createNewHouse() {
        while (true) {
            System.out.println("How do you want to create a house?\n1 - Yourself\n2 - Automatically\n0 - Exit");
            int userChoice = enterNumInt();
            while (userChoice < 0 || userChoice > 2) {
                System.out.print("There is no such operation\nTry again - ");
                userChoice = enterNumInt();
            }
            if (userChoice == 0) {
                break;
            }
            House newHouse;
            House house;
            int houseNumber;
            switch (userChoice) {
                case 1:
                    System.out.print("Enter the house number - ");

                    houseNumber = enterNumInt();
                    house = HouseService.findHouse(houseNumber);
                    while(house.getHouseNumber() != 0){
                        System.out.print("House with this number already exists. Try again - ");
                        houseNumber = enterNumInt();
                        house = HouseService.findHouse(houseNumber);
                    }
                    newHouse = new YourselfHouseCreationFactory(houseNumber).createHouse();
                    HouseRepository.addHouse(newHouse);
                    break;
                case 2:
                    houseNumber = (int) (2 + Math.random() * 50);
                    house = HouseService.findHouse(houseNumber);
                    while(house.getHouseNumber() != 0){
                        System.out.print("House with this number already exists. Try again - ");
                        houseNumber = enterNumInt();
                        house = HouseService.findHouse(houseNumber);
                    }
                    newHouse = new AutoHouseCreationFactory(houseNumber).createHouse();
                    HouseRepository.addHouse(newHouse);
                    break;
            }
            System.out.println("Do you want to make another house?\n1 - Yes\n0 - No");
            userChoice = enterNumInt();
            while (userChoice < 0 || userChoice > 1) {
                System.out.print("There is no such operation\nTry again - ");
                userChoice = enterNumInt();
            }
            if (userChoice == 0)
                break;
        }
        System.out.println();
    }

    static void outputHouse() {
        HouseRepository.printHouseNumbers();
        System.out.print("Enter the number of the house you want to get information about - ");
        int houseNumber = enterNumInt();
        House house = HouseService.findHouse(houseNumber);
        while(house.getHouseNumber() == 0){
            System.out.print("There is no house with this number, try again - ");
            houseNumber = enterNumInt();
            house = HouseService.findHouse(houseNumber);
        }
        while (true) {
            System.out.println("----------------------------------------------------------");
            System.out.println("What information do you want to get?");
            System.out.println("1 - Number of floors\n2 - Number of apartments\n3 - Number of tenants");
            System.out.println("4 - Total area of the house\n5 - All information about each apartment\n0 - Exit");
            System.out.print("Enter the number of the operation you want to perform - ");
            int userChoice = enterNumInt();
            if (userChoice == 0)
                break;
            while (userChoice < 0 || userChoice >= 6) {
                System.out.print("There is no such operation\nTry again - ");
                userChoice = enterNumInt();
            }
            if (userChoice == 0)
                break;

            switch (userChoice) {
                case 1:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Number of floors - " + house.getFloors().size());
                    break;
                case 2:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Number of apartments - " + HouseService.countApartments(house));
                    break;
                case 3:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Number of tenants - " + HouseService.countTenantsHouse(house));
                    break;
                case 4:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Total area of the house - " + String.format("%.2f", HouseService.calculateAreaHouse(house)));
                    break;
                case 5:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("All information\n" + house.toString());
                    break;
            }
            System.out.println("----------------------------------------------------------");
            System.out.println("Do you want to display any more information?\n1 - Yes\n0 - No");
            userChoice = enterNumInt();
            while (userChoice < 0 || userChoice > 1) {
                System.out.print("There is no such operation\nTry again - ");
                userChoice = enterNumInt();
            }
            if (userChoice == 0)
                break;
        }
    }

    static void outputAllHouse() {
        System.out.println("----------------------------------------------------------");
        for(Map.Entry<Integer, House> house : HouseRepository.getHouses().entrySet()){
            System.out.print("House number - " + house.getKey());
            System.out.print("   Square - " + String.format("%.2f", HouseService.calculateAreaHouse(house.getValue())));
            System.out.println("   Number of tenants - " + HouseService.countTenantsHouse(house.getValue()));
        }
    }

    static void deleteHouse() {
        System.out.println("----------------------------------------------------------");
        HouseRepository.printHouseNumbers();
        System.out.print("Enter the house number - ");
        int houseNumber = enterNumInt();
        House house = HouseService.findHouse(houseNumber);
        while (house.getHouseNumber() == 0) {
            System.out.print("There is no house with this number, try again - ");
            houseNumber = enterNumInt();
            house = HouseService.findHouse(houseNumber);
        }
        HouseRepository.removeHouse(houseNumber);
        System.out.println("Deletion completed successfully");
    }

    static void compare() {
        while (true) {
            System.out.println("----------------------------------------------------------");
            System.out.println("What will you compare?\n1 - Houses\n2 - Apartments in the house\n0 - Exit");
            int userChoice = enterNumInt();
            if (userChoice == 0)
                break;
            while (userChoice < 0 || userChoice >= 3) {
                System.out.print("There is no such operation\nTry again - ");
                userChoice = enterNumInt();
            }
            if (userChoice == 0)
                break;

            switch (userChoice) {
                case 1:
                    if (HouseRepository.isRepositoryHaveTwoHouses()) {
                        System.out.println("Not enough houses to compare");
                    } else {
                        HouseRepository.printHouseNumbers();
                        System.out.print("Enter the number of the first house - ");
                        int houseNumber = enterNumInt();
                        House house1 = HouseService.findHouse(houseNumber);
                        while (house1.getHouseNumber() == 0) {
                            System.out.print("There is no house with this number, try again - ");
                            houseNumber = enterNumInt();
                            house1 = HouseService.findHouse(houseNumber);
                        }
                        System.out.print("Enter the number of the second house - ");
                        houseNumber = enterNumInt();
                        House house2 = HouseService.findHouse(houseNumber);
                        while (house2.getHouseNumber() == 0) {
                            System.out.print("There is no house with this number, try again - ");
                            houseNumber = enterNumInt();
                            house2 = HouseService.findHouse(houseNumber);
                        }
                        System.out.println("----------------------------------------------------------");
                        System.out.println(HouseService.compareHouses(house1, house2));
                    }
                    break;
                case 2:
                    HouseRepository.printHouseNumbers();
                    System.out.print("Enter the number of the house - ");
                    int houseNumber = enterNumInt();
                    House house = HouseService.findHouse(houseNumber);
                    while (house.getHouseNumber() == 0) {
                        System.out.print("There is no house with this number, try again - ");
                        houseNumber = enterNumInt();
                        house = HouseService.findHouse(houseNumber);
                    }
                    int numberOfApartments = HouseService.countApartments(house);
                    if (numberOfApartments < 2)
                        System.out.println("There are not enough apartments in the house for comparison");
                    else {
                        System.out.println("Number of existing apartments:");
                        for (int i = 0; i < numberOfApartments; ++i) {
                            System.out.print(i + 1 + " ");
                        }
                        System.out.println();
                        int apartmentNumber1;
                        int apartmentNumber2;
                        do {
                            System.out.print("Enter the number of the first apartment - ");
                            apartmentNumber1 = enterNumInt();
                            while (apartmentNumber1 <= 0 || apartmentNumber1 > numberOfApartments) {
                                System.out.print("There is no apartment with this number, try again - ");
                                apartmentNumber1 = enterNumInt();
                            }
                            System.out.print("Enter the number of the second apartment - ");
                            apartmentNumber2 = enterNumInt();
                            while (apartmentNumber2 <= 0 || apartmentNumber2 > numberOfApartments) {
                                System.out.print("There is no apartment with this number, try again - ");
                                apartmentNumber2 = enterNumInt();
                            }
                            if (apartmentNumber1 == apartmentNumber2) {
                                System.out.println("It is impossible to compare identical apartments. Try again");
                            }
                        } while (apartmentNumber1 == apartmentNumber2);
                        System.out.println("----------------------------------------------------------");
                        System.out.println(HouseService.compareApartments(house, apartmentNumber1, apartmentNumber2));
                    }
                    break;
            }
        }
    }

    static int enterNumInt() {
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                break;
            } else {
                System.out.println("Enter the number!");
                scanner.next();
            }
        }
        return userInput;
    }

    static double enterNumDouble(){
        Scanner scanner = new Scanner(System.in);
        double userInput = 0;
        while(scanner.hasNext()){
            if(scanner.hasNextDouble()){
                userInput = scanner.nextDouble();
                break;
            }
            else {
                System.out.println("Enter the number!");
                scanner.next();
            }
        }
        return userInput;
    }
}