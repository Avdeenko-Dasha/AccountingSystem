package com.avdeenko.userInterface;

import com.avdeenko.factory.AutoHouseCreationFactory;
import com.avdeenko.factory.ManualHouseCreationFactory;
import com.avdeenko.models.model.House;
import com.avdeenko.repository.HouseRepository;
import com.avdeenko.service.HouseService;

public class UserInterfaceImpl implements UserInterface {
    public void start() {
        System.out.println("Welcome to the accounting system!");
        HouseRepository houseRepository = new HouseRepository();
        while (true) {
            System.out.println("---------------------------Menu----------------------------");
            System.out.println("1 - Create a house\n2 - Display information about the house\n3 - All created houses" +
                "\n4 - Delete a house\n" + "5 - Compare objects" + "\n0 - Exit" +
                "\nEnter the number of the operation you want to perform - ");
            int userChoice = enterTheOperation(5);
            if (userChoice == 0)
                break;
            switch (userChoice) {
                case 1:
                    createNewHouse();
                    break;
                case 2:
                    if (houseRepository.isRepositoryNonNull())
                        outputHouse();
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 3:
                    if (houseRepository.isRepositoryNonNull())
                        outputAllHouse();
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 4:
                    if (houseRepository.isRepositoryNonNull())
                        deleteHouse();
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 5:
                    if (houseRepository.isRepositoryNonNull())
                        compare();
                    else
                        System.out.println("You don't have any homes created!");
                    break;
            }
        }

        System.out.println("Goodbye. Good luck!");
        System.out.println("----------------------------------------------------------");
    }

    @Override
    public void createNewHouse() {
        while (true) {
            HouseService houseService = HouseService.getHouseService();
            HouseRepository houseRepository = new HouseRepository();
            System.out.println("How do you want to create a house?\n1 - Yourself\n2 - Automatically\n0 - Exit");
            int userChoice = enterTheOperation(2);
            if (userChoice == 0) {
                break;
            }
            House house;
            int houseNumber;
            switch (userChoice) {
                case 1:
                    System.out.print("Enter the house number - ");

                    houseNumber = UserInterface.enterNumInt();
                    house = houseService.findHouse(houseNumber);
                    while(house != null){
                        System.out.print("House with this number already exists. Try again - ");
                        houseNumber = UserInterface.enterNumInt();
                        house = houseService.findHouse(houseNumber);
                    }
                    new ManualHouseCreationFactory(houseNumber).createHouse();
                    break;
                case 2:
                    houseNumber = (int) (2 + Math.random() * 50);
                    house = houseService.findHouse(houseNumber);
                    while(house != null){
                        System.out.print("House with this number already exists. Try again - ");
                        houseNumber = UserInterface.enterNumInt();
                        house = houseService.findHouse(houseNumber);
                    }
                    new AutoHouseCreationFactory(houseNumber).createHouse();
                    break;
            }
            System.out.println("Do you want to make another house?\n1 - Yes\n0 - No");
            userChoice = enterTheOperation(1);
            if (userChoice == 0)
                break;
        }
        System.out.println();
    }

    @Override
    public void outputHouse() {
        HouseService houseService = HouseService.getHouseService();
        HouseRepository houseRepository = new HouseRepository();
        houseRepository.printHouseNumbers();
        System.out.print("Enter the number of the house you want to get information about - ");
        House house = houseService.findHouse(enterHouseNumber());
        while (true) {
            System.out.println("----------------------------------------------------------");
            System.out.println("What information do you want to get?");
            System.out.println("1 - Number of floors\n2 - Number of apartments\n3 - Number of tenants");
            System.out.println("4 - Total area of the house\n5 - All information about each apartment\n0 - Exit");
            System.out.print("Enter the number of the operation you want to perform - ");
            int userChoice = enterTheOperation(5);
            if (userChoice == 0)
                break;

            switch (userChoice) {
                case 1:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Number of floors - " + house.getFloors().size());
                    break;
                case 2:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Number of apartments - " + houseService.countApartments(house));
                    break;
                case 3:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Number of tenants - " + houseService.countTenantsHouse(house));
                    break;
                case 4:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Total area of the house - " + String.format("%.2f", houseService.calculateAreaHouse(house)));
                    break;
                case 5:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("All information\n" + house);
                    break;
            }
            System.out.println("----------------------------------------------------------");
            System.out.println("Do you want to display any more information?\n1 - Yes\n0 - No");
            userChoice = enterTheOperation(1);
            if (userChoice == 0)
                break;
        }
    }

    @Override
    public void outputAllHouse() {
        HouseService houseService = HouseService.getHouseService();
        HouseRepository houseRepository = new HouseRepository();
        System.out.println("----------------------------------------------------------");
        for(House house : houseRepository.getHouses()){
            System.out.print("House number - " + house.getNumber());
            System.out.print("   Square - " + String.format("%.2f", houseService.calculateAreaHouse(house)));
            System.out.println("   Number of tenants - " + houseService.countTenantsHouse(house));
        }
    }

    @Override
    public void deleteHouse() {
        System.out.println("----------------------------------------------------------");
        HouseRepository houseRepository = new HouseRepository();
        houseRepository.printHouseNumbers();
        System.out.print("Enter the house number - ");
        int houseNumber = enterHouseNumber();
        houseRepository.deleteHouse(houseNumber);
        System.out.println("Deletion completed successfully");
    }

    @Override
    public void compare() {
        while (true) {
            HouseService houseService = HouseService.getHouseService();
            HouseRepository houseRepository = new HouseRepository();
            System.out.println("----------------------------------------------------------");
            System.out.println("What will you compare?\n1 - Houses\n2 - Apartments in the house\n0 - Exit");
            int userChoice = enterTheOperation(2);
            if (userChoice == 0)
                break;

            switch (userChoice) {
                case 1:
                    if (houseRepository.isRepositoryHaveTwoHouses()) {
                        System.out.println("Not enough houses to compare");
                    } else {
                        houseRepository.printHouseNumbers();
                        System.out.print("Enter the number of the first house - ");
                        House house1 = houseService.findHouse(enterHouseNumber());
                        System.out.print("Enter the number of the second house - ");
                        House house2 = houseService.findHouse(enterHouseNumber());
                        System.out.println("----------------------------------------------------------");
                        System.out.println(houseService.compareHouses(house1, house2));
                    }
                    break;
                case 2:
                    houseRepository.printHouseNumbers();
                    System.out.print("Enter the number of the house - ");
                    House house = houseService.findHouse(enterHouseNumber());
                    int numberOfApartments = houseService.countApartments(house);
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
                            apartmentNumber1 = enterApartmentNumber(numberOfApartments);
                            System.out.print("Enter the number of the second apartment - ");
                            apartmentNumber2 = enterApartmentNumber(numberOfApartments);
                            if (apartmentNumber1 == apartmentNumber2) {
                                System.out.println("It is impossible to compare identical apartments. Try again");
                            }
                        } while (apartmentNumber1 == apartmentNumber2);
                        System.out.println("----------------------------------------------------------");
                        System.out.println(houseService.compareApartments(house, apartmentNumber1, apartmentNumber2));
                    }
                    break;
            }
        }
    }

    private int enterTheOperation(int lastOperation){
        int userChoice = UserInterface.enterNumInt();
        while (userChoice < 0 || userChoice > lastOperation) {
            System.out.print("There is no such operation\nTry again - ");
            userChoice = UserInterface.enterNumInt();
        }
        return userChoice;
    }

    private int enterHouseNumber(){
        HouseService houseService = HouseService.getHouseService();
        int houseNumber = UserInterface.enterNumInt();
        House house = houseService.findHouse(houseNumber);
        while (house == null) {
            System.out.print("There is no house with this number, try again - ");
            houseNumber = UserInterface.enterNumInt();
            house = houseService.findHouse(houseNumber);
        }
        return houseNumber;
    }

    private int enterApartmentNumber(int numberOfApartments){
        int apartmentNumber = UserInterface.enterNumInt();
        while (apartmentNumber <= 0 || apartmentNumber > numberOfApartments) {
            System.out.print("There is no apartment with this number, try again - ");
            apartmentNumber = UserInterface.enterNumInt();
        }
        return apartmentNumber;
    }
}