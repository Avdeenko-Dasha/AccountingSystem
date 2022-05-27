package com.avdeenko.repository;

import com.avdeenko.model.model.House;

import java.util.List;
import java.util.Optional;

public interface HouseRepository {
    void creat(House object);
    Optional<House> readByKey(Integer key);
    List<House> readAll();
    void deleteByKey(Integer key);
    void deleteAll();
}
