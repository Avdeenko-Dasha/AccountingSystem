package Building;

import java.util.ArrayList;

/**
 * Class Apartment this is...
 * @author Avdeenko Dasha
 */
public class Apartment implements Instruments{
    /**Static variable that helps determine the apartment number*/
    private static int staticNumApartment = 1;
    /**Variable that stores the apartment number*/
    private int numApartment = 0;
    /**Variable that stores the area of the apartment*/
    private double squareApartment = 0;
    /**Variable that stores the number of residents in the apartment*/
    private int numResidents = 0;
    /**Variable that stores the number of rooms in the apartment*/
    private int numRoom = 0;
    /**Variable that stores the number of apartments per floor*/
    private int numApartmentPerFloor = 0;
    /**ArrayList storing the structure of the apartment*/
    private ArrayList<Room> apartment;
    /**ArrayList storing the area of rooms on the apartments*/
    public static ArrayList<ArrayList<Double>> squareRooms = new ArrayList<>(0);




    Apartment(){
        setNumApartment(staticNumApartment);
        staticNumApartment++;
        setNumResidents(0);
        setNumRoom(0);
        apartment = new ArrayList<>(0);
        numApartmentPerFloor = 0;
    }

    public static void setStaticNumApartment(int staticNumApartment) {
        Apartment.staticNumApartment = staticNumApartment;
    }

    public double getSquareApartment() {
        return squareApartment;
    }

    public int getNumResidents() {
        return numResidents;
    }

    public Apartment setNumResidents(int numResidents) {
        this.numResidents = numResidents;
        return null;
    }

    public int getNumApartment() {
        return numApartment;
    }

    public void setNumApartment(int numApartment) {
        this.numApartment = numApartment;
    }

    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }


    /**
     * Method compares apartments by area and number of residents
     * @param obj the object to compare with
     */
    public void compare(Object obj){
        if(!(obj instanceof Apartment))
            System.out.println("Error! Trying to compare different objects");
        else if(squareApartment ==0 || ((Apartment) obj).squareApartment ==0)
            System.out.println("Error! Empty object");
        else if(equals(obj))
            System.out.println("Apartment at the same");
        else {
            double squareApartment2 = ((Apartment) obj).squareApartment;
            int    numResidents2 = ((Apartment) obj).numResidents;
            int    numApartment2 = ((Apartment) obj).numApartment;
            int    numRoom2 = ((Apartment) obj).numRoom;

            if (squareApartment > squareApartment2){
                System.out.println("The area of the apartment " + numApartment +
                        " is larger than the area of the apartment " + numApartment2);
            } else if (squareApartment < squareApartment2) {
                System.out.println("The area of the apartment " + numApartment +
                        " is less than the area of the apartment " + numApartment2);
            }else {
                System.out.println("The area of the apartment " + numApartment +
                        " is equal to the area of the apartment " + numApartment2);
            }
            System.out.println("Apartment " + numApartment + " - " + squareApartment +
                    "    Apartment " + numApartment2 + " - " + squareApartment2 + "\n");

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

            if (numRoom > numRoom2) {
                System.out.println("The number of rooms in the apartment " + numApartment +
                        " is greater than the number of rooms in the apartment " + numApartment2);
            }else if (numRoom < numRoom2) {
                System.out.println("The number of rooms in the apartment " + numApartment +
                        " is less than the number of rooms in the apartment " + numApartment2);
            } else {
                System.out.println("The number of rooms in the apartment " + numApartment +
                        " is equal than the number of rooms in the apartment " + numApartment2);
            }
            System.out.println("Apartment " + numApartment + " - " + numRoom +
                    "    Apartment " + numApartment2 + " - " + numRoom2 + "\n");
        }
    }

    public double calculateArea(){
        double square = 0;
        for(int i = 0; i < numRoom; ++i){
            square += apartment.get(i).getSquareRoom();
        }
        return square;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Apartment)) return false;
        if(squareApartment == 0 || ((Apartment) obj).squareApartment == 0) return false;
        return (squareApartment == ((Apartment) obj).squareApartment &&
                numResidents == ((Apartment) obj).numResidents &&
                numRoom == ((Apartment) obj).numRoom);
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Apartment - " + getNumApartment() + " Square - " + String.format("%.2f", squareApartment) + " Residents - " + getNumResidents() + "\n");
        for(int i = 0; i < numRoom; ++i){
            str.append(apartment.get(i).toString());
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
            newApartment.apartment = new ArrayList<>(newApartment.numRoom);
            if ("automatically".equalsIgnoreCase(method)) {
                if(newApartment.getNumApartment()==1){
                    if (!squareRooms.isEmpty())
                        squareRooms.clear();
                }
                if (newApartment.getNumApartment() >= 1 && newApartment.getNumApartment() <= newApartment.numApartmentPerFloor) {
                    ArrayList<Double> squareRoom = new ArrayList<>();
                    for (int i = 0; i < newApartment.numRoom; ++i) {
                        square = 10 + Math.random() * 70;
                        Room room = new Room.BuilderRoom().setSquareRoom(square).build();
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
                        Room room = new Room.BuilderRoom().setSquareRoom(squareRooms.get(numApartment-1).get(i)).build();
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
                        Room room = new Room.BuilderRoom().setSquareRoom(square).build();
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
                        Room room = new Room.BuilderRoom().setSquareRoom(squareRooms.get(numApartment-1).get(i)).build();
                        newApartment.apartment.add(room);
                    }
                }
            }
            newApartment.squareApartment = newApartment.calculateArea();
            return this;
        }

        public Apartment build(){
            return newApartment;
        }
    }
}
