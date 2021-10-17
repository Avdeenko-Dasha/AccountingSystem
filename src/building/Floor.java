package building;
import java.util.ArrayList;

/**
 * Class Floor this is...
 * @author Avdeenko Dasha
 */
public class Floor {
    /**Static variable that helps determine the floor number*/
    private static int staticNumFloor = 1;
    /**Variable that stores the floor number*/
    private int numFloor = 0;
    /**Variable that stores the number of apartments in the floor*/
    private int numApartment = 0;
    /**ArrayList storing the structure of the floor*/
    private ArrayList<Apartment> floor = new ArrayList<Apartment>();

    Floor(){
        setNumFloor(staticNumFloor);
        staticNumFloor++;
        setNumApartment(0);
        floor = new ArrayList<Apartment>(0);
    }
    Floor(int numApartment){
        setNumFloor(staticNumFloor);
        staticNumFloor++;
        setNumApartment(numApartment);
        floor = new ArrayList<Apartment>(numApartment);

        double square = 30 + Math.random()*200;
        int numResidents = (int)(Math.random()*5);

        for(int i = 0; i < numApartment; ++i){
            Apartment apartment = new Apartment(square, numResidents);
            floor.add(i, apartment);
            square = 30 + Math.random()*200;
            numResidents = (int)(Math.random()*5);
        }
    }

    public int getNumFloor() {
        return numFloor;
    }

    public void setNumFloor(int numFloor) {
        this.numFloor = numFloor;
    }

    public int getNumApartment() {
        return numApartment;
    }

    public void setNumApartment(int numApartment) {
        this.numApartment = numApartment;
    }

    /**
     * Method calculateArea calculates the area of the floor
     */
    public double calculateArea(){
        double square = 0;
        for(int i = 0; i < numApartment; ++i){
            square += floor.get(i).getSquareApartment();
        }
        return square;
    }

    /**
     * Method countTenants calculates the number of residents in the floor
     */
    public int countTenants(){
        int tenants = 0;
        for(int i = 0; i < numApartment; ++i){
            tenants += floor.get(i).getNumResidents();
        }
        return tenants;
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Floor)) return false;
        if(numApartment==0 || ((Floor) obj).numApartment==0) return false;
        if(numApartment == ((Floor) obj).numApartment)
        {
            for(int i = 0; i < numApartment; ++i){
                if(!floor.get(i).equals(((Floor) obj).floor.get(i)))
                    return false;
            }
            return  true;
        } else return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < numApartment; ++i){
            str.append(floor.get(i).toString());
            str.append("\n");
        }
        return "Floor - " + numFloor + "\n" + str.toString();
    }
}
