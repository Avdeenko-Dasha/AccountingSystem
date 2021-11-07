package Building;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest {

    @Test
    void calculateAreaTest() {
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
        Floor floor1 = new Floor();
        floor1.addApartment(apartment1);
        floor1.addApartment(apartment2);
        floor1.addApartment(apartment3);
        Floor floor2 = new Floor();
        floor2.addApartment(apartment1);
        floor2.addApartment(apartment2);
        floor2.addApartment(apartment3);
        House house = new House();
        house.addFloor(floor1);
        house.addFloor(floor2);
        assertEquals(171.6, house.calculateArea());
    }
    @Test
    void countTenantsTest() {
        Apartment apartment1 = new Apartment();
        apartment1.setNumResidents(4);
        Apartment apartment2 = new Apartment();
        apartment2.setNumResidents(5);
        Apartment apartment3 = new Apartment();
        apartment3.setNumResidents(1);
        Floor floor1 = new Floor();
        floor1.addApartment(apartment1);
        floor1.addApartment(apartment2);
        floor1.addApartment(apartment3);
        Floor floor2 = new Floor();
        floor2.addApartment(apartment1);
        floor2.addApartment(apartment2);
        floor2.addApartment(apartment3);
        House house = new House();
        house.addFloor(floor1);
        house.addFloor(floor2);
        assertEquals(20, house.countTenants());
    }
}
