package Building;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<House> arrayHouses = new ArrayList<>(0);
        System.out.println("Welcome to the accounting system!");
        while (true) {
            System.out.println("1 - Create a house\n2 - Display information about the house\n3 - All created houses" +
                    "4 - Delete a house\n" + "5 - Compare objects\n0 - Exit");
            System.out.print("Enter the number of the operation you want to perform - ");
            int button = enterNumInt();
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
                    createNewHouse(arrayHouses);
                    break;
                case 2:
                    if (arrayHouses.size() != 0)
                        outputHouse(arrayHouses);
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 3:
                    if (arrayHouses.size() != 0)
                        outputAllHouse(arrayHouses);
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 4:
                    if (arrayHouses.size() != 0)
                        deleteHouse(arrayHouses);
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 5:
                    compare(arrayHouses);
                    break;
            }
        }
        System.out.println("Goodbye. Good luck!");
    }

    public static void createNewHouse(ArrayList<House> arrayHouse) {
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
            int numFloor = 0;
            switch (button) {
                case 1:
                    System.out.print("Enter the number of floors - ");
                    numFloor = enterNumInt();
                    House newHouse = new House.BuilderHouse().setNumFloor(numFloor).methodOfCreation("yourself").build();
                    arrayHouse.add(newHouse);
                    break;
                case 2:
                    numFloor = (int) (2 + Math.random() * 10);
                    House newHouses = new House.BuilderHouse().setNumFloor(numFloor).methodOfCreation("automatically").build();
                    arrayHouse.add(newHouses);
                    break;
            }
            System.out.println("Do you want to make another house?\n1 - Yes\n0 - No");
            button = enterNumInt();
            if (button == 0)
                break;
        }
        System.out.println();
    }

    public static void outputHouse(ArrayList<House> arrayHouse){
        System.out.println("Do you know the number of the house you want to get information about?\n1 - Yes\n0 - No");
        int button = enterNumInt();
        while(button < 0 || button > 1){
            System.out.print("There is no such operation\nTry again - ");
            button = enterNumInt();
        }
        if(button == 0){
            System.out.println("Number of existing houses:");
            for(int i = 0; i < arrayHouse.size(); ++i){
                System.out.print(arrayHouse.get(i).getNumHouse()+" ");
            }
            System.out.println();
        }
        System.out.print("Enter the number of the house you want to get information about - ");
        int index = enterNumInt();
        while(index <= 0 || index > arrayHouse.size()){
            System.out.print("There is no house with this number, try again - ");
            index = enterNumInt();
        }
        while(true) {
            System.out.println("What information do you want to get?");
            System.out.println("1 - Number of floors\n2 - Number of apartments\n3 - Number of tenants");
            System.out.println("4 - Total area of the house\n5 - All information about each apartment\n0 - Exit");
            System.out.print("Enter the number of the operation you want to perform - ");
            button = enterNumInt();
            if(button == 0)
                break;
            while(button < 0 || button >= 6){
                System.out.print("There is no such operation\nTry again - ");
                button = enterNumInt();
            }
            if(button == 0)
                break;

            switch(button){
                case 1:
                    System.out.println("Number of floors - " + arrayHouse.get(index - 1).getNumFloor());
                    break;
                case 2:
                    System.out.println("Number of apartments - " + arrayHouse.get(index - 1).getNumApartment());
                    break;
                case 3:
                    System.out.println("Number of tenants - " + arrayHouse.get(index - 1).getNumOfTenants());
                    break;
                case 4:
                    System.out.println("Total area of the house - " + arrayHouse.get(index - 1).getSquareHouse());
                    break;
                case 5:
                    System.out.println("All information\n" + arrayHouse.get(index - 1).toString());
                    break;
            }
            System.out.println("Do you want to display any more information?\n1 - Yes\n0 - No");
            button = enterNumInt();
            if(button == 0)
                break;
        }
        System.out.println();
    }

    public static void outputAllHouse(ArrayList<House> arrayHouse){
        for(int i = 0; i < arrayHouse.size(); ++i){
            System.out.print("House number - " + arrayHouse.get(i).getNumHouse());
            System.out.print("   Square - " + arrayHouse.get(i).getSquareHouse());
            System.out.println("   Number of tenants - " + arrayHouse.get(i).getNumOfTenants());
        }
        System.out.println();
    }

    public static void deleteHouse(ArrayList<House> arrayHouse){
        System.out.println("Do you know the number of the house you want to delete?\n1 - Yes\n0 - No");
        int button = enterNumInt();
        while(button < 0 || button > 1){
            System.out.print("There is no such operation\nTry again - ");
            button = enterNumInt();
        }
        if(button == 0){
            System.out.println("Number of existing houses:");
            for(int i = 0; i < arrayHouse.size(); ++i){
                System.out.print(arrayHouse.get(i).getNumHouse()+" ");
            }
            System.out.println();
        }
        System.out.println("Enter the house number - ");
        int index = enterNumInt();
        while(index <= 0 || index > arrayHouse.size()){
            System.out.print("There is no house with this number, try again - ");
            index = enterNumInt();
        }
        arrayHouse.remove(index-1);
        System.out.println("Deletion completed successfully");
    }

    public static void compare(ArrayList<House> arrayHouse) {
        while (true) {
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
                        System.out.print(arrayHouse.get(i).getNumHouse() + " ");
                    }
                    System.out.println();
                }
                if (arrayHouse.size() > 1) {
                    System.out.print("Enter the number of the first house - ");
                    int index1 = enterNumInt();
                    while (index1 <= 0 || index1 > arrayHouse.size()) {
                        System.out.print("There is no house with this number, try again - ");
                        index1 = enterNumInt();
                    }
                    System.out.print("Enter the number of the second house - ");
                    int index2 = enterNumInt();
                    while (index2 <= 0 || index2 > arrayHouse.size()) {
                        System.out.print("There is no house with this number, try again - ");
                        index2 = enterNumInt();
                    }
                    arrayHouse.get(index1 - 1).compare(arrayHouse.get(index2 - 1));
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
                        System.out.print(arrayHouse.get(i).getNumHouse() + " ");
                    }
                    System.out.println();
                }
                System.out.print("Enter the number of the house - ");
                int index = enterNumInt();
                while (index <= 0 || index > arrayHouse.size()) {
                    System.out.print("There is no house with this number, try again - ");
                    index = enterNumInt();
                }
                if (arrayHouse.get(index - 1).getNumApartment() < 2)
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
                        for (int i = 0; i < arrayHouse.get(index - 1).getNumApartment(); ++i) {
                            System.out.print(i + 1 + " ");
                        }
                        System.out.println();
                    }
                    int index1 = 0;
                    int index2 = 0;
                    do {
                        System.out.print("Enter the number of the first apartment - ");
                        index1 = enterNumInt();
                        while (index1 <= 0 || index1 > arrayHouse.get(index - 1).getNumApartment()) {
                            System.out.print("There is no apartment with this number, try again - ");
                            index1 = enterNumInt();
                        }
                        System.out.print("Enter the number of the second apartment - ");
                        index2 = enterNumInt();
                        while (index2 <= 0 || index2 > arrayHouse.get(index-1).getNumApartment()) {
                            System.out.print("There is no apartment with this number, try again - ");
                            index2 = enterNumInt();
                        }
                        if (index1 == index2) {
                            System.out.println("It is impossible to compare identical apartments. Try again");
                        }
                    } while (index1 == index2);
                    arrayHouse.get(index - 1).getApartment(index1).compare(arrayHouse.get(index - 1).getApartment(index2));
                }
            }
            System.out.println();
        }
    }

    public static int enterNumInt(){
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while(sc.hasNext()){
            if(sc.hasNextInt()){
                n = sc.nextInt();
                break;
            }
            else {
                System.out.println("Enter the number!");
                sc.next();
            }
        }
        return n;
    }

    public static double enterNumDouble(){
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
