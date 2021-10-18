package building;
import java.util.ArrayList;

/**
 * Class House this is...
 * @author Avdeenko Dasha
 */
public class House {
    /**Static variable that helps determine the house number*/
    private static int  staticNumHouse = 1;
    /**Variable that stores the house number*/
    private int     numHouse = 0;
    /**Variable that stores the number of floors in the house*/
    private int     numFloor = 0;
    /**ArrayList storing the structure of the house*/
    private ArrayList<Floor> house = new ArrayList<Floor>();

    House()
    {
        numHouse = staticNumHouse;
        staticNumHouse++;
        numFloor = 0;
        house = new ArrayList<Floor>(0);
        Floor floor = new Floor();
        floor.setStaticNumFloor(1);
        Apartment apartment = new Apartment();
        apartment.setStaticNumApartment(1);
    }

    public int getNumFloor() {
        return numFloor;
    }

    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }

    /**
     * Method calculateArea calculates the area of the house
     */
    public double calculateArea(){
        double square = 0;
        for(int i = 0; i < numFloor; ++i){
            square += house.get(i).calculateArea();
        }
        square = Math.round(square * 100.0) / 100.0;
        return square;
    }

    /**
     * Method countTenants calculates the number of residents in the house
     */
    public int countTenants(){
        int tenants = 0;
        for(int i = 0; i < numFloor; ++i){
            tenants += house.get(i).countTenants();
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

            if (squareHouse1 > squareHouse2) {
                System.out.println("The area of the house " + numHouse + " is larger than the area of the house " + ((House) obj).numHouse);
            } else if (squareHouse1 < squareHouse2) {
                System.out.println("The area of the house " + numHouse + " is less than the area of the house " + ((House) obj).numHouse);
            }else {
                System.out.println("The area of the house " + numHouse + " is equal to the area of the house " + ((House) obj).numHouse);
            }

            if (numResidents1 > numResidents2) {
                System.out.println("The number of tenants in the house " + numHouse + " is greater than the number of tenants in the house " + ((House) obj).numHouse);
            }else if (numResidents1 < numResidents2) {
                System.out.println("The number of tenants in the house " + numHouse + " is less than the number of tenants in the house " + ((House) obj).numHouse);
            } else {
                System.out.println("The number of tenants in the house " + numHouse + " is equal than the number of tenants in the house " + ((House) obj).numHouse);
            }
        }
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof House)) return false;
        if(numFloor==0 || ((House) obj).numFloor==0) return false;
        if(numFloor == ((House) obj).numFloor)
        {
            for(int i = 0; i < numFloor; ++i){
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
            newHouse.house = new ArrayList<Floor>(numFloor);
            int numApartment = (int)(2 + Math.random() * 4);
            for(int i = 0; i < numFloor; ++i){
                Floor floor = new Floor.BuilderFloor().setNumApartment(numApartment).build();
                newHouse.house.add(i, floor);
            }
            return this;
        }

        public House build(){
            return newHouse;
        }
    }

}
