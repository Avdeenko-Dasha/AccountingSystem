package Building;

public class Apartment {
    private static int staticNumApartment = 1;
    private int numApartment = 0;
    private double square = 0;
    private int numResidents = 0;


    Apartment(){
        setNumApartment(staticNumApartment);
        staticNumApartment++;
        setSquare(0);
        setNumResidents(0);
    }

    Apartment(double square) {
        setNumApartment(staticNumApartment);
        staticNumApartment++;
        setSquare(square);
    }

    Apartment(double square, int numResidents){
        setNumApartment(staticNumApartment);
        staticNumApartment++;
        setSquare(square);
        setNumResidents(numResidents);
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public int getNumResidents() {
        return numResidents;
    }

    public void setNumResidents(int numResidents) {
        this.numResidents = numResidents;
    }

    public int getNumApartment() {
        return numApartment;
    }

    public void setNumApartment(int numApartment) {
        this.numApartment = numApartment;
    }

    public void compare(Object obj){
        if(!(obj instanceof Apartment))
            System.out.println("Error! Trying to compare different objects");
        else if(square==0 || ((Apartment)obj).square==0)
            System.out.println("Error! Empty object");
        else if(equals(obj))
            System.out.println("Apartment at the same");
        else {
            if (square > ((Apartment) obj).square)
                System.out.println("The area of apartment " + numApartment + " is larger than the area of apartment " + ((Apartment) obj).numApartment);
            if (square < ((Apartment) obj).square)
                System.out.println("The area of apartment " + numApartment + " is less than the area of apartment " + ((Apartment) obj).numApartment);
            if (square == ((Apartment) obj).square)
                System.out.println("The area of apartment " + numApartment + " is equal to the area of apartment " + ((Apartment) obj).numApartment);
            if (numResidents > ((Apartment) obj).numResidents)
                System.out.println("The number of tenants in apartment " + numApartment + " is greater than the number of tenants in apartment " + ((Apartment) obj).numApartment);
            if (numResidents < ((Apartment) obj).numResidents)
                System.out.println("The number of tenants in apartment " + numApartment + " is less than the number of tenants in apartment " + ((Apartment) obj).numApartment);
            if (numResidents > ((Apartment) obj).numResidents)
                System.out.println("The number of tenants in apartment " + numApartment + " is equal than the number of tenants in apartment " + ((Apartment) obj).numApartment);
        }
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Apartment)) return false;
        if(square==0 || ((Apartment)obj).square==0) return false;
        return (square == ((Apartment)obj).square && numResidents == ((Apartment)obj).numResidents && numApartment == ((Apartment)obj).numApartment);
    }

    public String toString() {
        return "Apartment - " + numApartment + " Square - " + String.format("%.2f", square) + " Residents - " + numResidents;
    }
}
