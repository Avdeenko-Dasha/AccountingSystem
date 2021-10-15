package Building;

import java.util.ArrayList;

public class House {
    private int numFloor = 0;
    private ArrayList<Floor> house = new ArrayList<Floor>();

    House(){
        numFloor = 0;
        house = new ArrayList<Floor>(0);
    }

    House(int numFloor){
        this.numFloor = numFloor;
        house = new ArrayList<Floor>(numFloor);
        int numApartment = (int)(2 + Math.random() * 4);
        for(int i = 0; i < numFloor; ++i){
            Floor floor = new Floor(numApartment);
            house.add(i, floor);
        }
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
        return "House:" + "\n" + str.toString();
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
