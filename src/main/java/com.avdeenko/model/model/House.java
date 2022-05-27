package com.avdeenko.model.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class House extends BaseModel {
    private List<Floor> floors = new ArrayList<>();

    public House(){
        super(null);
    }

    public House(Integer numberOfHouse) {
        super(numberOfHouse);
    }

    public House(Integer numberOfHouse, List<Floor> floors) {
        super(numberOfHouse);
        this.floors=floors;
    }

    public String toString() {
        StringBuilder floorsToString = new StringBuilder("House " + super.getNumber() + ":<p>");
        for (Floor floor : floors) {
            floorsToString.append(floor.toString());
        }
        return floorsToString.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof House)) return false;
        if (floors.size() == 0 || ((House) obj).floors.size() == 0) return false;
        if (floors.size() == ((House) obj).floors.size()) {
            for (int i = 0; i < floors.size(); ++i) {
                if (!floors.get(i).equals(((House) obj).floors.get(i)))
                    return false;
            }
            return true;
        } else return false;
    }
}
