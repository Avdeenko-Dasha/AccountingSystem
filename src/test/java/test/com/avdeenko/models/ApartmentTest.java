package com.avdeenko.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApartmentTest {

    @Test
    void calculateArea() {
        Room room1 = new Room();
        room1.setSquareRoom(13.2);
        Room room2 = new Room();
        room2.setSquareRoom(15.3);
        Room room3 = new Room();
        room3.setSquareRoom(21);
        Apartment apartment = new Apartment();
        apartment.addRoom(room1);
        apartment.addRoom(room2);
        apartment.addRoom(room3);
        assertEquals(49.5, apartment.calculateArea());
    }
}