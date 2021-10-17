package Building;

import java.util.ArrayList;

public class House {
    private  static int staticNumHouse = 1;
    private int numHouse = 0;
    private int numFloor = 0;
    private double squareHouse = 0;
    private int numResidents = 0;
    private ArrayList<Floor> house = new ArrayList<Floor>();

    House(){
        numHouse = staticNumHouse;
        staticNumHouse++;
        numFloor = 0;
        squareHouse = 0;
        numResidents = 0;
        house = new ArrayList<Floor>(0);
    }

    House(int numFloor){
        numHouse = staticNumHouse;
        staticNumHouse++;
        this.numFloor = numFloor;
        house = new ArrayList<Floor>(numFloor);
        int numApartment = (int)(2 + Math.random() * 4);
        for(int i = 0; i < numFloor; ++i){
            Floor floor = new Floor(numApartment);
            house.add(i, floor);
        }
        squareHouse = Square();
        numResidents = Tenants();
    }

    public int getNumFloor() {
        return numFloor;
    }

    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }


    public double Square(){
        double square = 0;
        for(int i = 0; i < numFloor; ++i){
            square += house.get(i).Square();
        }
        square = Math.round(square * 100.0) / 100.0;
        return square;
    }

    public int Tenants(){
        int tenants = 0;
        for(int i = 0; i < numFloor; ++i){
            tenants += house.get(i).Tenants();
        }
        return tenants;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < numFloor; ++i){
            str.append(house.get(i).toString());
            str.append("\n");
        }
        return "House " + numHouse + ":" + "\n" + str.toString();
    }

    public void compare(Object obj){
        if(!(obj instanceof House))
            System.out.println("Error! Trying to compare different objects");
        else if(squareHouse==0 || ((House)obj).squareHouse==0)
            System.out.println("Error! Empty object");
        else if(equals(obj))
            System.out.println("The houses are the same");
        else {
            if (squareHouse > ((House) obj).squareHouse)
                System.out.println("The area of the house " + numHouse + " is larger than the area of the house " + ((House) obj).numHouse);
            if (squareHouse < ((House) obj).squareHouse)
                System.out.println("The area of the house " + numHouse + " is less than the area of the house " + ((House) obj).numHouse);
            if (squareHouse == ((House) obj).squareHouse)
                System.out.println("The area of the house " + numHouse + " is equal to the area of the house " + ((House) obj).numHouse);
            if (numResidents > ((House) obj).numResidents)
                System.out.println("The number of tenants in the house " + numHouse + " is greater than the number of tenants in the house " + ((House) obj).numHouse);
            if (numResidents < ((House) obj).numResidents)
                System.out.println("The number of tenants in the house " + numHouse + " is less than the number of tenants in the house " + ((House) obj).numHouse);
            if (numResidents > ((House) obj).numResidents)
                System.out.println("The number of tenants in the house " + numHouse + " is equal than the number of tenants in the house " + ((House) obj).numHouse);
        }
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof House)) return false;
        if(numFloor==0 || ((House)obj).numFloor==0) return false;
        if(numFloor == ((House)obj).numFloor)
        {
            for(int i = 0; i < numFloor; ++i){
                if(!house.get(i).equals(((House)obj).house.get(i)))
                    return false;
            }
            return  true;
        } else return false;
    }

}
