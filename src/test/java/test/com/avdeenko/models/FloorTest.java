package com.avdeenko.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorTest {

    @Test
    void calculateArea() {
        Room room1 = new Room();
        room1.setSquareRoom(13.2);
        Room room2 = new Room();
        room2.setSquareRoom(15.3);
        Room room3 = new Room();
        room3.setSquareRoom(21);
        Apartment apartment1 = new Apartment();
        apartment1.addRoom(room1);
        apartment1.addRoom(room2);
        apartment1.addRoom(room3);
        Apartment apartment2 = new Apartment();
        apartment2.addRoom(room2);
        Apartment apartment3 = new Apartment();
        apartment3.addRoom(room3);
        Floor floor = new Floor();
        floor.addApartment(apartment1);
        floor.addApartment(apartment2);
        floor.addApartment(apartment3);
        assertEquals(85.8, floor.calculateArea());
    }

    @Test
    void countTenants() {
        Apartment apartment1 = new Apartment();
        apartment1.setNumResidents(4);
        Apartment apartment2 = new Apartment();
        apartment2.setNumResidents(5);
        Apartment apartment3 = new Apartment();
        apartment3.setNumResidents(1);
        Floor floor = new Floor();
        floor.addApartment(apartment1);
        floor.addApartment(apartment2);
        floor.addApartment(apartment3);
        assertEquals(10, floor.countTenants());
    }
}