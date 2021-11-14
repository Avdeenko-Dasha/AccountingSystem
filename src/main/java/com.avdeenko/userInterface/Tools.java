package com.avdeenko.userInterface;

import com.avdeenko.factory.AutoHouseCreationFactory;
import com.avdeenko.factory.YourselfHouseCreationFactory;
import com.avdeenko.models.House;

import java.util.ArrayList;
import java.util.Scanner;

public interface Tools {
    static void createNewHouse(ArrayList<House> arrayHouse) {
        while (true) {
            System.out.println("How do you want to create a house?\n1 - Yourself\n2 - Automatically\n0 - Exit");
            int button = enterNumInt();
            while (button < 0 || button > 2) {
                System.out.print("There is no such operation\nTry again - ");
                button = enterNumInt();
            }
            if (button == 0) {
                break;
            }
            House newHouse;
            int numHouse;
            int index;
            switch (button) {
                case 1:
                    System.out.print("Enter the house number - ");
                    numHouse = enterNumInt();
                    index = findHouse(numHouse, arrayHouse);
                    while (index >= 0 && index < arrayHouse.size()) {
                        System.out.print("House with this number already exists. Try again - ");
                        numHouse = enterNumInt();
                        index = findHouse(numHouse, arrayHouse);
                    }
                    newHouse = new YourselfHouseCreationFactory(numHouse).createHouse();
                    arrayHouse.add(newHouse);
                    break;
                case 2:
                    numHouse = (int) (2 + Math.random() * 50);
                    index = findHouse(numHouse, arrayHouse);
                    while (index >= 0 && index < arrayHouse.size()) {
                        numHouse = (int) (2 + Math.random() * 50);
                        index = findHouse(numHouse, arrayHouse);
                    }
                    newHouse = new AutoHouseCreationFactory(numHouse).createHouse();
                    arrayHouse.add(newHouse);
                    break;
            }
            System.out.println("Do you want to make another house?\n1 - Yes\n0 - No");
            button = enterNumInt();
            while (button < 0 || button > 1) {
                System.out.print("There is no such operation\nTry again - ");
                button = enterNumInt();
            }
            if (button == 0)
                break;
        }
        System.out.println();
    }

    static void outputHouse(ArrayList<House> arrayHouse) {
        System.out.println("Do you know the number of the house you want to get information about?\n1 - Yes\n0 - No");
        int button = enterNumInt();
        while (button < 0 || button > 1) {
            System.out.print("There is no such operation\nTry again - ");
            button = enterNumInt();
        }
        if (button == 0) {
            System.out.println("Number of existing houses:");
            for (int i = 0; i < arrayHouse.size(); ++i) {
                System.out.print(arrayHouse.get(i).getHouseNumber() + " ");
            }
            System.out.println();
        }
        System.out.print("Enter the number of the house you want to get information about - ");
        button = enterNumInt();
        int index = findHouse(button, arrayHouse);
        while (index < 0 || index > arrayHouse.size()) {
            System.out.print("There is no house with this number, try again - ");
            button = enterNumInt();
            index = findHouse(button, arrayHouse);
        }
        while (true) {
            System.out.println("----------------------------------------------------------");
            System.out.println("What information do you want to get?");
            System.out.println("1 - Number of floors\n2 - Number of apartments\n3 - Number of tenants");
            System.out.println("4 - Total area of the house\n5 - All information about each apartment\n0 - Exit");
            System.out.print("Enter the number of the operation you want to perform - ");
            button = enterNumInt();
            if (button == 0)
                break;
            while (button < 0 || button >= 6) {
                System.out.print("There is no such operation\nTry again - ");
                button = enterNumInt();
            }
            if (button == 0)
                break;

            switch (button) {
                case 1:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("Number of floors - " + arrayHouse.get(index).getFloors().size());
                    break;
                case 2:
                    System.out.println("----------------------------------------------------------");
                    //System.out.println("Number of apartments - " + arrayHouse.get(index).countApartments());
                    break;
                case 3:
                    System.out.println("----------------------------------------------------------");
                    //System.out.println("Number of tenants - " + arrayHouse.get(index).countTenants());
                    break;
                case 4:
                    System.out.println("----------------------------------------------------------");
                    //System.out.println("Total area of the house - " + arrayHouse.get(index).calculateArea());
                    break;
                case 5:
                    System.out.println("----------------------------------------------------------");
                    System.out.println("All information\n" + arrayHouse.get(index).toString());
                    break;
            }
            System.out.println("----------------------------------------------------------");
            System.out.println("Do you want to display any more information?\n1 - Yes\n0 - No");
            button = enterNumInt();
            while (button < 0 || button > 1) {
                System.out.print("There is no such operation\nTry again - ");
                button = enterNumInt();
            }
            if (button == 0)
                break;
        }
    }

    static void outputAllHouse(ArrayList<House> arrayHouse) {
        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < arrayHouse.size(); ++i) {
            System.out.print("House number - " + arrayHouse.get(i).getHouseNumber());
            //System.out.print("   Square - " + String.format("%.2f", arrayHouse.get(i).calculateArea()));
            //System.out.println("   Number of tenants - " + arrayHouse.get(i).countTenants());
        }
    }

    static void deleteHouse(ArrayList<House> arrayHouse) {
        System.out.println("----------------------------------------------------------");
        System.out.println("Do you know the number of the house you want to delete?\n1 - Yes\n0 - No");
        int button = enterNumInt();
        while (button < 0 || button > 1) {
            System.out.print("There is no such operation\nTry again - ");
            button = enterNumInt();
        }
        if (button == 0) {
            System.out.println("Number of existing houses:");
            for (int i = 0; i < arrayHouse.size(); ++i) {
                System.out.print(arrayHouse.get(i).getHouseNumber() + " ");
            }
            System.out.println();
        }
        System.out.print("Enter the house number - ");
        button = enterNumInt();
        int index = findHouse(button, arrayHouse);
        while (index < 0 || index > arrayHouse.size()) {
            System.out.print("There is no house with this number, try again - ");
            button = enterNumInt();
            index = findHouse(button, arrayHouse);
        }
        arrayHouse.remove(index);
        System.out.println("Deletion completed successfully");
    }

    static void compare(ArrayList<House> arrayHouse) {
        while (true) {
            System.out.println("----------------------------------------------------------");
            System.out.println("What will you compare?\n1 - Houses\n2 - Apartments in the house\n0 - Exit");
            int button = enterNumInt();
            if (button == 0)
                break;
            while (button < 0 || button >= 3) {
                System.out.print("There is no such operation\nTry again - ");
                button = enterNumInt();
            }
            if (button == 0)
                break;

            if (button == 1) {
                System.out.println("Do you know the number of the house you want to get information about?\n1 - Yes\n0 - No");
                button = enterNumInt();
                while (button < 0 || button > 1) {
                    System.out.print("There is no such operation\nTry again - ");
                    button = enterNumInt();
                }
                if (button == 0) {
                    System.out.println("Number of existing houses:");
                    for (int i = 0; i < arrayHouse.size(); ++i) {
                        System.out.print(arrayHouse.get(i).getHouseNumber() + " ");
                    }
                    System.out.println();
                }
                if (arrayHouse.size() > 1) {
                    System.out.print("Enter the number of the first house - ");
                    int numHouse = enterNumInt();
                    int index1 = findHouse(numHouse, arrayHouse);
                    while (index1 < 0 || index1 > arrayHouse.size()) {
                        System.out.print("There is no house with this number, try again - ");
                        numHouse = enterNumInt();
                        index1 = findHouse(numHouse, arrayHouse);
                    }
                    System.out.print("Enter the number of the second house - ");
                    numHouse = enterNumInt();
                    int index2 = findHouse(numHouse, arrayHouse);
                    while (index2 < 0 || index2 > arrayHouse.size()) {
                        System.out.print("There is no house with this number, try again - ");
                        numHouse = enterNumInt();
                        index2 = findHouse(numHouse, arrayHouse);
                    }
                    System.out.println("----------------------------------------------------------");
                    //arrayHouse.get(index1).compare(arrayHouse.get(index2));
                } else System.out.println("Not enough houses to compare");
            } else {
                System.out.println("Do you know the number of the house you want to get information about?\n1 - Yes\n0 - No");
                button = enterNumInt();
                while (button < 0 || button > 1) {
                    System.out.print("There is no such operation\nTry again - ");
                    button = enterNumInt();
                }
                if (button == 0) {
                    System.out.println("Number of existing houses:");
                    for (int i = 0; i < arrayHouse.size(); ++i) {
                        System.out.print(arrayHouse.get(i).getHouseNumber() + " ");
                    }
                    System.out.println();
                }
                System.out.print("Enter the number of the house - ");
                int numHouse = enterNumInt();
                int index = findHouse(numHouse, arrayHouse);
                while (index < 0 || index > arrayHouse.size()) {
                    System.out.print("There is no house with this number, try again - ");
                    numHouse = enterNumInt();
                    index = findHouse(numHouse, arrayHouse);
                }
                /*if (arrayHouse.get(index).countApartments() < 2)
                    System.out.println("There are not enough apartments in the house for comparison");
                else {
                    System.out.println("Do you know the number of the apartments\n1 - Yes\n0 - No");
                    button = enterNumInt();
                    while (button < 0 || button > 1) {
                        System.out.print("There is no such operation\nTry again - ");
                        button = enterNumInt();
                    }
                    if (button == 0) {
                        System.out.println("Number of existing apartments:");
                        for (int i = 0; i < arrayHouse.get(index).countApartments(); ++i) {
                            System.out.print(i + 1 + " ");
                        }
                        System.out.println();
                    }
                    int index1 = 0;
                    int index2 = 0;
                    do {
                        System.out.print("Enter the number of the first apartment - ");
                        index1 = enterNumInt();
                        while (index1 <= 0 || index1 > arrayHouse.get(index).countApartments()) {
                            System.out.print("There is no apartment with this number, try again - ");
                            index1 = enterNumInt();
                        }
                        System.out.print("Enter the number of the second apartment - ");
                        index2 = enterNumInt();
                        while (index2 <= 0 || index2 > arrayHouse.get(index).countApartments()) {
                            System.out.print("There is no apartment with this number, try again - ");
                            index2 = enterNumInt();
                        }
                        if (index1 == index2) {
                            System.out.println("It is impossible to compare identical apartments. Try again");
                        }
                    } while (index1 == index2);
                    System.out.println("----------------------------------------------------------");
                    arrayHouse.get(index).getApartment(index1).compare(arrayHouse.get(index).getApartment(index2));
                }*/
            }
        }
    }

    static int findHouse(int numHouse, ArrayList<House> houses) {
        int index = 0;
        while (index < houses.size()) {
            if (houses.get(index).getHouseNumber() == numHouse) {
                return index;
            }
            index += 1;
        }
        return houses.size() + 1;
    }

    static int enterNumInt() {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                break;
            } else {
                System.out.println("Enter the number!");
                sc.next();
            }
        }
        return n;
    }

    static double enterNumDouble(){
        Scanner sc = new Scanner(System.in);
        double n = 0;
        while(sc.hasNext()){
            if(sc.hasNextDouble()){
                n = sc.nextDouble();
                break;
            }
            else {
                System.out.println("Enter the number!");
                sc.next();
            }
        }
        return n;
    }
}