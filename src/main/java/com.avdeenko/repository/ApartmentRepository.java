package com.avdeenko.repository;

import com.avdeenko.model.model.Apartment;

import java.util.List;
import java.util.Optional;

public interface ApartmentRepository {
    void creat(Apartment object, Integer idHouse);
    Optional<Apartment> readByKey(Integer key, Integer numberHouse);
    List<Apartment> readAll();
    void deleteByKey(Integer key, Integer numberHouse);
    void deleteAll();
}
