package com.avdeenko.models;

import lombok.Data;

import java.util.List;

@Data
public class House {
    /**Variable that stores the house number*/
    private int houseNumber;
    /**List storing the structure of the house*/
    private List<Floor> floors;

    public House(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("House " + houseNumber + ":" + "\n");
        for(Floor floor : floors){
            str.append(floor.toString());
            str.append("\n");
        }
        // str.append("Square - " + calculateArea() + "\nNumber of tenants - " + countTenants() + "\n");
        return str.toString();
    }
    public boolean equals(Object obj) {
        if(!(obj instanceof House)) return false;
        if(floors.size()==0 || ((House) obj).floors.size()==0) return false;
        if(floors.size() == ((House) obj).floors.size())
        {
            for(int i = 0; i < floors.size(); ++i){
                if(!floors.get(i).equals(((House) obj).floors.get(i)))
                    return false;
            }
            return  true;
        } else return false;
    }
}
