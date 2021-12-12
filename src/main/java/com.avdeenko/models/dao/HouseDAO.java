package com.avdeenko.models.dao;

import com.avdeenko.models.model.House;
import lombok.Getter;

@Getter
public class HouseDAO extends BaseDAO {
    private final Integer numberOfFloors;

    public HouseDAO(House house){
        super(house.getNumber());
        this.numberOfFloors = house.getFloors().size();
    }
}
