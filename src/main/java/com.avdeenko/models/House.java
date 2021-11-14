package com.avdeenko.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class House implements Instruments{
    /**Variable that stores the house number*/
    private int     numHouse;
    /**Variable that stores the number of floors in the house*/
    private int numFloor;
    /**ArrayList storing the structure of the house*/
    private ArrayList<Floor> house;

    House()
    {
        house = new ArrayList<>(0);
        numFloor = 0;
        numHouse = 0;
        Floor floor = new Floor();
        floor.setStaticNumFloor(1);
        Apartment apartment = new Apartment();
        apartment.setStaticNumApartment(1);
    }

    public Apartment getApartment(int index){
        int numApartmentFloor = house.get(0).getNumApartment();
        int numFloorApartment = 0;
        int indexApartment = index;
        while(indexApartment > 0){
            indexApartment-=numApartmentFloor;
            numFloorApartment += 1;
        }
        return house.get(numFloorApartment - 1).getApartment(index);
    }

    public int countApartments(){ return house.get(0).getNumApartment() * house.size(); }

    public void addFloor(Floor floor){ house.add(floor); }

    /**
     * Method calculateArea calculates the area of the house
     */
    public double calculateArea(){
        double square = 0;
        for(Floor floor : house){
            square += floor.calculateArea();
        }
        return Math.round(square * 100.0) / 100.0;
    }

    /**
     * Method countTenants calculates the number of residents in the house
     */
    public int countTenants(){
        int tenants = 0;
        for(Floor floor : house){
            tenants += floor.countTenants();
        }
        return tenants;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("House " + numHouse + ":" + "\n");
        for(Floor floor : house){
            str.append(floor.toString());
            str.append("\n");
        }
        str.append("Square - " + calculateArea() + "\nNumber of tenants - " + countTenants() + "\n");
        return str.toString();
    }

    /**
     * Method compares houses by area and number of residents
     * @param obj the object to compare with
     */
    public void compare(Object obj){

        if(!(obj instanceof House))
            System.out.println("Error! Trying to compare different objects");
        else if(calculateArea()==0 || ((House) obj).calculateArea()==0)
            System.out.println("Error! Empty object");
        else if(equals(obj))
            System.out.println("The houses are the same");
        else {
            double squareHouse1 = calculateArea();
            double squareHouse2 = ((House) obj).calculateArea();
            int    numResidents1 = countTenants();
            int    numResidents2 = ((House) obj).countTenants();
            int    numFloor2 = ((House) obj).house.size();
            int    numHouse2 = ((House) obj).numHouse;

            if (squareHouse1 > squareHouse2) {
                System.out.println("The area of the house " + numHouse +
                        " is larger than the area of the house " + numHouse2);
            } else if (squareHouse1 < squareHouse2) {
                System.out.println("The area of the house " + numHouse +
                        " is less than the area of the house " + numHouse2);
            }else {
                System.out.println("The area of the house " + numHouse +
                        " is equal to the area of the house " + numHouse2);
            }
            System.out.println("House " + numHouse + " - " + squareHouse1 +
                    "    House " + numHouse2 + " - " + squareHouse2 + "\n");

            if (numResidents1 > numResidents2) {
                System.out.println("The number of tenants in the house " + numHouse +
                        " is greater than the number of tenants in the house " + numHouse2);
            }else if (numResidents1 < numResidents2) {
                System.out.println("The number of tenants in the house " + numHouse +
                        " is less than the number of tenants in the house " + numHouse2);
            } else {
                System.out.println("The number of tenants in the house " + numHouse +
                        " is equal than the number of tenants in the house " + numHouse2);
            }
            System.out.println("House " + numHouse + " - " + numResidents1 +
                    "    House " + numHouse2 + " - " + numResidents2 + "\n");

            if(house.size() > numFloor2){
                System.out.println("The number of floors in the house " + numHouse +
                        " is greater than the number of floors in the house " + numHouse2);
            }
            else if (house.size() < numFloor2){
                System.out.println("The number of floors in the house " + numHouse +
                        " is less than the number of floors in the house " + numHouse2);
            }
            else{
                System.out.println("The number of floors in the house " + numHouse +
                        " is equal than the number of floors in the house " + numHouse2);
            }
            System.out.println("House " + numHouse + " - " + house.size() +
                    "    House " + numHouse2 + " - " + numFloor2 + "\n");
        }
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof House)) return false;
        if(house.size()==0 || ((House) obj).house.size()==0) return false;
        if(house.size() == ((House) obj).house.size())
        {
            for(int i = 0; i < house.size(); ++i){
                if(!house.get(i).equals(((House) obj).house.get(i)))
                    return false;
            }
            return  true;
        } else return false;
    }

    public static class BuilderHouse{
        private House newHouse;

        public BuilderHouse(){
            newHouse = new House();
        }

        public BuilderHouse setNumFloor(int numFloor){
            newHouse.numFloor = numFloor;
            return this;
        }

        public BuilderHouse setNumHouse(int numHouse){
            newHouse.numHouse = numHouse;
            return this;
        }

        public BuilderHouse methodOfCreation(String method){
            int numApartment = 0;
            newHouse.house = new ArrayList<>();
            if("yourself".equalsIgnoreCase(method)){
                System.out.print("Enter the number of apartments on the floor - ");
                numApartment = Instruments.enterNumInt();
                for(int i = 0; i < newHouse.numFloor; ++i){
                    Floor floor = new Floor.BuilderFloor()
                            .setNumApartment(numApartment)
                            .methodOfCreation("yourself")
                            .build();
                    newHouse.house.add(floor);
                }
            }else if("automatically".equalsIgnoreCase(method))
            {
                numApartment = (int)(2 + Math.random() * 4);
                for(int i = 0; i < newHouse.numFloor; ++i){
                    Floor floor = new Floor.BuilderFloor()
                            .setNumApartment(numApartment)
                            .methodOfCreation("automatically")
                            .build();
                    newHouse.house.add(floor);
                }
            }
            return this;
        }

        public House build(){
            return newHouse;
        }
    }
}
