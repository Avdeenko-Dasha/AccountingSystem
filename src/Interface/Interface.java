package Interface;

import Building.House;
import java.util.ArrayList;

public class Interface implements Tools {
    public void start() {
        ArrayList<House> arrayHouses = new ArrayList<>(0);
        System.out.println("Welcome to the accounting system!");
        while (true) {
            System.out.println("---------------------------Menu----------------------------");
            System.out.println("1 - Create a house\n2 - Display information about the house\n3 - All created houses" +
                    "\n4 - Delete a house\n" + "5 - Compare objects\n0 - Exit");
            System.out.print("Enter the number of the operation you want to perform - ");
            int button = Tools.enterNumInt();
            if (button == 0)
                break;
            while (button < 0 || button >= 6) {
                System.out.print("There is no such operation\nTry again - ");
                button = Tools.enterNumInt();
            }
            if (button == 0)
                break;
            switch (button) {
                case 1:
                    Tools.createNewHouse(arrayHouses);
                    break;
                case 2:
                    if (arrayHouses.size() != 0)
                        Tools.outputHouse(arrayHouses);
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 3:
                    if (arrayHouses.size() != 0)
                        Tools.outputAllHouse(arrayHouses);
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 4:
                    if (arrayHouses.size() != 0)
                        Tools.deleteHouse(arrayHouses);
                    else
                        System.out.println("You don't have any homes created!");
                    break;
                case 5:
                    Tools.compare(arrayHouses);
                    break;
            }
        }
        System.out.println("Goodbye. Good luck!");
        System.out.println("----------------------------------------------------------");
    }
}

