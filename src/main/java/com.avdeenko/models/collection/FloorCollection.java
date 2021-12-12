package com.avdeenko.models.collection;

import com.avdeenko.models.model.Floor;
import com.avdeenko.models.dao.FloorDAO;
import lombok.Data;

import java.util.*;

@Data
public class FloorCollection {
    private List<FloorDAO> floors;

    public FloorCollection() {
        this.floors = new ArrayList<>();
    }

    public FloorCollection(List<FloorDAO> floors) {
        this.floors = floors;
    }

    public void addFloor(Floor floor) {
        floors.add(new FloorDAO(floor));
    }
}
