package com.avdeenko.userInterface;

import java.util.Scanner;

public interface UserInterface {
    void createNewHouse();

    void outputHouse();

    void outputAllHouse();

    void deleteHouse();

    void compare();

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