package Building;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

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
    /**Variable that stores the number of apartments in the house*/
    private int     numApartment = 0;
    /**Variable that stores the number of tenants in the house*/
    private int     numOfTenants = 0;
    /**Variable that stores area of the house*/
    private double     squareHouse = 0;
    /**ArrayList storing the structure of the house*/
    private ArrayList<Floor> house;



    House()
    {
        numHouse = staticNumHouse;
        staticNumHouse++;
        numFloor = 0;
        numApartment = 0;
        numOfTenants = 0;
        squareHouse = 0;
        house = new ArrayList<Floor>(0);
        Floor floor = new Floor();
        floor.setStaticNumFloor(1);
        Apartment apartment = new Apartment();
        apartment.setStaticNumApartment(1);
    }

    public int getNumFloor() {
        return numFloor;
    }

    public int getNumApartment() {
        return numApartment;
    }

    public int getNumHouse() {
        return numHouse;
    }

    public int getNumOfTenants() {
        return numOfTenants;
    }

    public double getSquareHouse() {
        return squareHouse;
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
        StringBuilder str = new StringBuilder("House " + numHouse + ":" + "\n");
        for(int i = 0; i < numFloor; ++i){
            str.append(house.get(i).toString());
            str.append("\n");
        }
        str.append("Square - " + getSquareHouse() + "\nNumber of tenants - " + getNumOfTenants() + "\n");
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

    private static int enterNumInt(){
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

    public static class BuilderHouse{
        private House newHouse;

        public BuilderHouse(){
            newHouse = new House();
        }

        public BuilderHouse setNumFloor(int numFloor){
            newHouse.numFloor = numFloor;
            return this;
        }
        public BuilderHouse methodOfCreation(String method){
            int sumApartment = 0;
            int numApartment = 0;
            newHouse.house = new ArrayList<>(newHouse.numFloor);

            if("yourself".equalsIgnoreCase(method)){
                System.out.print("Enter the number of apartments on the floor - ");
                numApartment = enterNumInt();
                for(int i = 0; i < newHouse.numFloor; ++i){
                    sumApartment += numApartment;
                    Floor floor = new Floor.BuilderFloor().setNumApartment(numApartment).methodOfCreation("yourself").build();
                    newHouse.house.add(floor);
                }
            }else if("automatically".equalsIgnoreCase(method))
            {
                numApartment = (int)(2 + Math.random() * 4);
                for(int i = 0; i < newHouse.numFloor; ++i){
                    sumApartment += numApartment;
                    Floor floor = new Floor.BuilderFloor().setNumApartment(numApartment).methodOfCreation("automatically").build();
                    newHouse.house.add(floor);
                }
            }
            newHouse.numApartment = sumApartment;
            newHouse.squareHouse = newHouse.calculateArea();
            newHouse.numOfTenants = newHouse.countTenants();
            return this;
        }

        public House build(){
            return newHouse;
        }
    }

}
