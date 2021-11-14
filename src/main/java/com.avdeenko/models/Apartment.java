package com.avdeenko.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Apartment implements Instruments{
    /**Static variable that helps determine the apartment number*/
    private static int staticNumApartment = 1;
    /**Variable that stores the apartment number*/
    private int numApartment = 0;
    /**Variable that stores the number of residents in the apartment*/
    private int numResidents = 0;
    /**Variable that stores the number of apartments per floor*/
    private int numApartmentPerFloor = 0;
    /**Variable that stores the number of apartment in the floor*/
    private int numRoom = 0;
    /**ArrayList storing the structure of the apartment*/
    private ArrayList<Room> apartment;
    /**ArrayList storing the area of rooms on the apartments*/
    public static ArrayList<ArrayList<Double>> squareRooms = new ArrayList<>(0);

    Apartment(){
        setNumApartment(staticNumApartment);
        staticNumApartment++;
        setNumResidents(0);
        numRoom = 0;
        apartment = new ArrayList<>(0);
        numApartmentPerFloor = 0;
    }

    public static void setStaticNumApartment(int staticNumApartment) { Apartment.staticNumApartment = staticNumApartment; }

    public void addRoom(Room room){ apartment.add(room); }

    /**
     * Method compares apartments by area and number of residents
     * @param obj the object to compare with
     */
    public void compare(Object obj){
        if(!(obj instanceof Apartment))
            System.out.println("Error! Trying to compare different objects");
        else if(calculateArea() == 0 || ((Apartment) obj).calculateArea() == 0)
            System.out.println("Error! Empty object");
        else if(equals(obj))
            System.out.println("Apartment at the same");
        else {
            double squareApartment1 = calculateArea();
            double squareApartment2 = ((Apartment) obj).calculateArea();
            int    numResidents2 = ((Apartment) obj).numResidents;
            int    numApartment2 = ((Apartment) obj).numApartment;
            int    numRoom1 = apartment.size();
            int    numRoom2 = ((Apartment) obj).apartment.size();

            if (squareApartment1 > squareApartment2){
                System.out.println("The area of the apartment " + numApartment +
                        " is larger than the area of the apartment " + numApartment2);
            } else if (squareApartment1 < squareApartment2) {
                System.out.println("The area of the apartment " + numApartment +
                        " is less than the area of the apartment " + numApartment2);
            }else {
                System.out.println("The area of the apartment " + numApartment +
                        " is equal to the area of the apartment " + numApartment2);
            }
            System.out.println("Apartment " + numApartment + " - " + String.format("%.2f", squareApartment1)  +
                    "    Apartment " + numApartment2 + " - " + String.format("%.2f", squareApartment2) + "\n");

            if (numResidents > numResidents2) {
                System.out.println("The number of tenants in the apartment " + numApartment +
                        " is greater than the number of tenants in the apartment " + numApartment2);
            }else if (numResidents < numResidents2) {
                System.out.println("The number of tenants in the apartment " + numApartment +
                        " is less than the number of tenants in the apartment " + numApartment2);
            } else {
                System.out.println("The number of tenants in the apartment " + numApartment +
                        " is equal than the number of tenants in the apartment " + numApartment2);
            }
            System.out.println("Apartment " + numApartment + " - " + numResidents +
                    "    Apartment " + numApartment2 + " - " + numResidents2 + "\n");

            if (numRoom1 > numRoom2) {
                System.out.println("The number of rooms in the apartment " + numApartment +
                        " is greater than the number of rooms in the apartment " + numApartment2);
            }else if (numRoom1 < numRoom2) {
                System.out.println("The number of rooms in the apartment " + numApartment +
                        " is less than the number of rooms in the apartment " + numApartment2);
            } else {
                System.out.println("The number of rooms in the apartment " + numApartment +
                        " is equal than the number of rooms in the apartment " + numApartment2);
            }
            System.out.println("Apartment " + numApartment + " - " + numRoom1 +
                    "    Apartment " + numApartment2 + " - " + numRoom2 + "\n");
        }
    }

    public double calculateArea(){
        double square = 0;
        for(Room room : apartment){
            square += room.getSquareRoom();
        }
        return square;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Apartment)) return false;
        if(calculateArea() == 0 || ((Apartment) obj).calculateArea() == 0) return false;
        if(apartment.size() == ((Apartment) obj).apartment.size()) {
            if(numResidents == ((Apartment) obj).numResidents) {
                for (int i = 0; i < apartment.size(); ++i) {
                    if (!apartment.get(i).equals(((Apartment) obj).apartment.get(i)))
                        return false;
                }
                return true;
            } return false;
        }else return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Apartment - " + getNumApartment() + " Square - " + String.format("%.2f", calculateArea()) + " Residents - " + getNumResidents() + "\n");
        for(Room room: apartment){
            str.append(room.toString());
            str.append("\n");
        }
        return str.toString();
    }

    public static class BuilderApartment{
        private Apartment newApartment;

        public BuilderApartment(){
            newApartment = new Apartment();
        }

        public BuilderApartment setNumResidents(int numResidents){
            newApartment.numResidents = numResidents;
            return this;
        }

        public BuilderApartment setNumRoom(int numRoom){
            newApartment.numRoom = numRoom;
            return this;
        }

        public BuilderApartment setNumApartmentPerFloor(int numApartmentPerFloor){
            newApartment.numApartmentPerFloor = numApartmentPerFloor;
            return this;
        }

        public Apartment.BuilderApartment methodOfCreation(String method) {
            double square = 0;
            if ("automatically".equalsIgnoreCase(method)) {
                if(newApartment.getNumApartment()==1){
                    if (!squareRooms.isEmpty())
                        squareRooms.clear();
                }
                if (newApartment.getNumApartment() >= 1 && newApartment.getNumApartment() <= newApartment.numApartmentPerFloor) {
                    ArrayList<Double> squareRoom = new ArrayList<>();
                    for (int i = 0; i < newApartment.numRoom; ++i) {
                        square = 10 + Math.random() * 70;
                        Room room = new Room.BuilderRoom()
                                .setSquareRoom(square)
                                .build();
                        newApartment.apartment.add(room);
                        squareRoom.add(square);
                    }
                    squareRooms.add(squareRoom);
                } else {
                    for (int i = 0; i < newApartment.numRoom; ++i) {
                        int numApartment = newApartment.numApartment;
                        while(numApartment>0){
                            numApartment-=newApartment.numApartmentPerFloor;
                        }
                        numApartment += newApartment.numApartmentPerFloor;
                        Room room = new Room.BuilderRoom()
                                .setSquareRoom(squareRooms.get(numApartment-1).get(i))
                                .build();
                        newApartment.apartment.add(room);
                    }
                }
            } else if ("yourself".equalsIgnoreCase(method)) {
                if(newApartment.getNumApartment()==1){
                    if (!squareRooms.isEmpty())
                        squareRooms.clear();
                }
                if (newApartment.getNumApartment() >= 1 && newApartment.getNumApartment() <= newApartment.numApartmentPerFloor) {
                    ArrayList<Double> squareRoom = new ArrayList<>();
                    for (int i = 0; i < newApartment.numRoom; ++i) {
                        System.out.print("Enter the area of room - ");
                        square = Instruments.enterNumDouble();
                        squareRoom.add(square);
                        Room room = new Room.BuilderRoom()
                                .setSquareRoom(square)
                                .build();
                        newApartment.apartment.add(room);
                    }
                    squareRooms.add(squareRoom);
                } else {
                    for (int i = 0; i < newApartment.numRoom; ++i) {
                        int numApartment = newApartment.numApartment;
                        while(numApartment>0){
                            numApartment-=newApartment.numApartmentPerFloor;
                        }
                        numApartment += newApartment.numApartmentPerFloor;
                        Room room = new Room.BuilderRoom()
                                .setSquareRoom(squareRooms.get(numApartment-1).get(i))
                                .build();
                        newApartment.apartment.add(room);
                    }
                }
            }
            return this;
        }

        public Apartment build(){
            return newApartment;
        }
    }
}
