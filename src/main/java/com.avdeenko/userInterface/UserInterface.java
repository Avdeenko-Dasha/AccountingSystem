package com.avdeenko.userInterface;

import com.avdeenko.repository.HouseRepository;

public class UserInterface implements Tools {
    public void start() {
        System.out.println("Welcome to the accounting system!");
        while (true) {
            System.out.println("---------------------------Menu----------------------------");
            System.out.println("1 - Create a house\n2 - Display information about the house\n3 - All created houses" +
                    "\n4 - Delete a house\n" + "5 - Compare objects\n0 - Exit");
            System.out.print("Enter the number of the operation you want to perform - ");
            int userChoice = Tools.enterNumInt();
            if (userChoice == 0)
                break;
            while (userChoice < 0 || userChoice >= 6) {
                System.out.print("There is no such operation\nTry again - ");
                userChoice = Tools.enterNumInt();
            }
            if (userChoice == 0)
                break;
            switch (userChoice) {
                case 1:
                    Tools.createNewHouse();
                    break;
                case 2:
                    if (!HouseRepository.isRepositoryNull())
                        Tools.outputHouse();
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 3:
                    if (!HouseRepository.isRepositoryNull())
                        Tools.outputAllHouse();
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 4:
                    if (!HouseRepository.isRepositoryNull())
                        Tools.deleteHouse();
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 5:
                    if (!HouseRepository.isRepositoryNull())
                        Tools.compare();
                    else
                        System.out.println("You don't have any homes created!");
                    break;
            }
        }
        System.out.println("Goodbye. Good luck!");
        System.out.println("----------------------------------------------------------");
    }
}
