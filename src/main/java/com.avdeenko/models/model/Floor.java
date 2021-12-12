package com.avdeenko.models.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor extends BaseModel {
    private Integer numberHouse;
    private List<Apartment> apartments = new ArrayList<>();

    public Floor(Integer numberHouse, Integer numberFloor) {
        super(numberFloor);
        this.numberHouse = numberHouse;
    }

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
        StringBuilder apartmentsToString = new StringBuilder(System.lineSeparator() + "Floor - " + super.getNumber());
        for(Apartment apartment : apartments){
            apartmentsToString.append(apartment.toString());
        }
        return apartmentsToString.toString() + System.lineSeparator();
    }
}
