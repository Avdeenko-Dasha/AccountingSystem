package com.avdeenko.models;

import lombok.Data;

import java.util.List;

@Data
public class Floor {
    /**Variable that stores the floor number*/
    private int numberFloor;
    /**List storing the structure of the floor*/
    private List<Apartment> apartments;

    public boolean equals(Object obj) {
        if(!(obj instanceof Floor)) return false;
        if(apartments.size()==0 || ((Floor) obj).apartments.size()==0) return false;
        if(apartments.size() == ((Floor) obj).apartments.size())
        {
            for(int i = 0; i < apartments.size(); ++i){
                if(!apartments.get(i).equals(((Floor) obj).apartments.get(i)))
                    return false;
            }
            return  true;
        } else return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder("Floor - " + numberFloor + "\n");
        for(Apartment apartment : apartments){
            str.append(apartment.toString());
            str.append("\n");
        }
        return str.toString();
    }
}