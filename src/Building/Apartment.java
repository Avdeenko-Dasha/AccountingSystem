package Building;

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
    /**Variable that stores the number of residents in the house*/
    private int numResidents = 0;


    Apartment(){
        setNumApartment(staticNumApartment);
        staticNumApartment++;
        setSquareApartment(0);
        setNumResidents(0);
    }


    public static void setStaticNumApartment(int staticNumApartment) {
        Apartment.staticNumApartment = staticNumApartment;
    }

    public double getSquareApartment() {
        return squareApartment;
    }

    public void setSquareApartment(double squareApartment) {
        this.squareApartment = squareApartment;
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
        }
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Apartment)) return false;
        if(squareApartment ==0 || ((Apartment) obj).squareApartment ==0) return false;
        return (squareApartment == ((Apartment) obj).squareApartment && numResidents == ((Apartment) obj).numResidents && numApartment == ((Apartment) obj).numApartment);
    }

    public String toString() {
        return "Apartment - " + getNumApartment() + " Square - " + String.format("%.2f", squareApartment) + " Residents - " + getNumResidents();
    }

    public static class BuilderApartment{
        private Apartment newApartment;

        public BuilderApartment(){
            newApartment = new Apartment();
        }

        public BuilderApartment setSquareApartment(double squareApartment){
            newApartment.squareApartment = squareApartment;
            return this;
        }

        public BuilderApartment setNumResidents(int numResidents){
            newApartment.numResidents = numResidents;
            return this;
        }

        public Apartment build(){
            return newApartment;
        }
    }
}
