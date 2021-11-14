package com.avdeenko.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Floor implements Instruments{
    /**Static variable that helps determine the floor number*/
    private static int staticNumFloor = 1;
    /**Variable that stores the floor number*/
    private int numFloor = 0;
    /**Variable that stores the number of apartment in the floor*/
    private int numApartment = 0;
    /**ArrayList storing the structure of the floor*/
    private ArrayList<Apartment> floor;
    /**ArrayList storing the number of rooms in the apartment*/
    public static ArrayList<Integer> numRooms = new ArrayList<>(0);

    Floor(){
        setNumFloor(staticNumFloor);
        staticNumFloor++;
        numApartment = 0;
        floor = new ArrayList<>(0);
    }

    public static void setStaticNumFloor(int staticNumFloor) { Floor.staticNumFloor = staticNumFloor; }

    public Apartment getApartment(int index){
        for(Apartment apartment : floor){
            if(apartment.getNumApartment() == index)
                return apartment;
        }
        return floor.get(0);
    }

    public void addApartment(Apartment apartment){ floor.add(apartment); }


    /**
     * Method calculateArea calculates the area of the floor
     */
    public double calculateArea(){
        double square = 0;
        for(Apartment apartment : floor){
            square += apartment.calculateArea();
        }
        return square;
    }

    /**
     * Method countTenants calculates the number of residents in the floor
     */
    public int countTenants(){
        int tenants = 0;
        for(Apartment apartment : floor){
            tenants += apartment.getNumResidents();
        }
        return tenants;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Floor)) return false;
        if(floor.size()==0 || ((Floor) obj).floor.size()==0) return false;
        if(floor.size() == ((Floor) obj).floor.size())
        {
            for(int i = 0; i < floor.size(); ++i){
                if(!floor.get(i).equals(((Floor) obj).floor.get(i)))
                    return false;
            }
            return  true;
        } else return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Floor - " + getNumFloor() + "\n");
        for(Apartment apartment : floor){
            str.append(apartment.toString());
            str.append("\n");
        }
        return str.toString();
    }

    public static class BuilderFloor {
        private Floor newFloor;

        public BuilderFloor() {
            newFloor = new Floor();
        }

        public BuilderFloor setNumApartment(int numApartment) {
            newFloor.numApartment = numApartment;
            return this;
        }

        public BuilderFloor methodOfCreation(String method) {
            int numResidents = 0;
            int numRoom = 0;
            newFloor.floor = new ArrayList<>();
            if ("automatically".equalsIgnoreCase(method)) {
                if (newFloor.getNumFloor() == 1) {
                    if (!numRooms.isEmpty())
                        numRooms.clear();
                    for (int i = 0; i < newFloor.numApartment; ++i) {
                        numResidents = (int) (Math.random() * 5);
                        numRoom = (int) (1 + Math.random() * 4);
                        Apartment apartment = new Apartment.BuilderApartment()
                                .setNumResidents(numResidents)
                                .setNumRoom(numRoom)
                                .setNumApartmentPerFloor(newFloor.numApartment)
                                .methodOfCreation("automatically")
                                .build();
                        newFloor.floor.add(apartment);
                        numRooms.add(numRoom);
                    }
                } else {
                    for (int i = 0; i < newFloor.numApartment; ++i) {
                        numResidents = (int) (Math.random() * 5);
                        Apartment apartment = new Apartment.BuilderApartment()
                                .setNumResidents(numResidents)
                                .setNumRoom(numRooms.get(i))
                                .setNumApartmentPerFloor(newFloor.numApartment)
                                .methodOfCreation("automatically")
                                .build();
                        newFloor.floor.add(i, apartment);
                    }
                }
            } else if ("yourself".equalsIgnoreCase(method)) {
                if (newFloor.getNumFloor() == 1) {
                    if (!numRooms.isEmpty())
                        numRooms.clear();
                    for (int i = 0; i < newFloor.numApartment; ++i) {
                        System.out.print("Enter the number of rooms in the apartment - ");
                        numRoom = Instruments.enterNumInt();
                        numRooms.add(numRoom);
                    }
                    for (int i = 0; i < newFloor.numApartment; ++i) {
                        System.out.println("Apartment " + (i + 1));
                        System.out.print("Enter the number of tenants in the apartment - ");
                        numResidents = Instruments.enterNumInt();

                        Apartment apartment = new Apartment.BuilderApartment()
                                .setNumRoom(numRooms.get(i))
                                .setNumResidents(numResidents)
                                .setNumApartmentPerFloor(newFloor.numApartment)
                                .methodOfCreation("yourself").build();
                        newFloor.floor.add(apartment);
                    }
                } else {
                    for (int i = 0; i < newFloor.numApartment; ++i) {
                        System.out.println("Apartment " + (newFloor.numFloor * newFloor.numApartment - newFloor.numApartment + i + 1));
                        System.out.print("Enter the number of tenants in the apartment - ");
                        numResidents = Instruments.enterNumInt();

                        Apartment apartment = new Apartment.BuilderApartment()
                                .setNumRoom(numRooms.get(i))
                                .setNumResidents(numResidents)
                                .setNumApartmentPerFloor(newFloor.numApartment)
                                .methodOfCreation("yourself").build();
                        newFloor.floor.add(apartment);
                    }
                }
            }
            return this;
        }

        public Floor build() {
            return newFloor;
        }
    }
}