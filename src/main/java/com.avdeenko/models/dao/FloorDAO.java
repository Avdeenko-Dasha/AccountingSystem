package com.avdeenko.models.dao;

import com.avdeenko.models.model.Floor;
import lombok.Getter;

@Getter
public class FloorDAO extends BaseDAO {
    private final Integer numberHouse;
    private final Integer numberOfApartments;

    public FloorDAO(Floor floor) {
        super(floor.getNumber());
        this.numberHouse = floor.getNumberHouse();
        this.numberOfApartments = floor.getApartments().size();
    }
}
