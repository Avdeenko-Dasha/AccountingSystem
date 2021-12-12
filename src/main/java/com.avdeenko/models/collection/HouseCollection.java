package com.avdeenko.models.collection;

import com.avdeenko.models.dao.HouseDAO;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class HouseCollection {
    private Set<HouseDAO> houses;

    public HouseCollection() {
        this.houses = new HashSet<>();
    }

    public HouseCollection(Set<HouseDAO> houses) {
//        this.houses = houses.stream()
//            .map(HouseDAO::new)
//            .collect(Collectors.toSet());
        /*for (House house : houses) {
            HouseDAO houseDAO = new HouseDAO(house);
            this.houses.add(houseDAO);
        }*/
        this.houses = houses;
    }


    public void addHouse(HouseDAO house) {
        this.houses.add(house);
    }

}
