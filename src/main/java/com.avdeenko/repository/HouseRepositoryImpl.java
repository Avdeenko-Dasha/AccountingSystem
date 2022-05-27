package com.avdeenko.repository;

import com.avdeenko.database.ConnectionPool;
import com.avdeenko.database.Const;
import com.avdeenko.model.model.Apartment;
import com.avdeenko.model.model.Floor;
import com.avdeenko.model.model.House;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HouseRepositoryImpl implements HouseRepository{
    private final ApartmentRepositoryImpl apartmentRepository = ApartmentRepositoryImpl.getApartmentRepository();
    private final ConnectionPool connectionPool = new ConnectionPool();
    private static HouseRepositoryImpl houseRepository;

    private HouseRepositoryImpl() {
    }

    public static HouseRepositoryImpl getHouseRepository() {
        if (houseRepository == null) {
            houseRepository = new HouseRepositoryImpl();
        }
        return houseRepository;
    }

    @Override
    public void creat(House object) {
        String insert = "INSERT INTO " + Const.HOUSE_TABLE + "("
            + Const.HOUSE_NUMBER_HOUSE + "," + Const.HOUSE_NUMBER_OF_FLOORS + ")" + "VALUES(?,?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(insert);
            prSt.setInt(1, object.getNumber());
            prSt.setInt(2, object.getFloors().size());

            prSt.executeUpdate();

            ResultSet resultSet = null;
            String select = "SELECT " + Const.HOUSE_ID + " FROM " + Const.HOUSE_TABLE + ";";
            prSt = connection.prepareStatement(select);
            resultSet = prSt.executeQuery();


            int idHouse = 0;
            while (resultSet.next()){
                idHouse = resultSet.getInt("id");
            }

            for (Floor floor:object.getFloors()) {
                for (Apartment apartment: floor.getApartments()) {
                    apartmentRepository.creat(apartment, idHouse);
                }
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<House> readByKey(Integer numberOfHouse) {
        String select = "SELECT " + Const.HOUSE_NUMBER_HOUSE + "," + Const.HOUSE_NUMBER_OF_FLOORS + ","
            + Const.APARTMENT_NUMBER_FLOOR + "," + Const.APARTMENT_NUMBER_APARTMENT + ","
            + Const.APARTMENT_SQUARE_APARTMENT + "," + Const.APARTMENT_RESIDENT_COUNT
            + " FROM " + Const.HOUSE_TABLE +  " INNER JOIN " + Const.APARTMENT_TABLE
            + " ON " + Const.HOUSE_ID + " = " + Const.APARTMENT_ID_HOUSE
            + " WHERE " + Const.HOUSE_NUMBER_HOUSE + "=" + numberOfHouse + ";";

        Optional<House> houseOptional = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = prSt.executeQuery();
            if(resultSet.next()) {
                houseOptional = createHouse(resultSet, numberOfHouse);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseOptional;
    }

    @Override
    public List<House> readAll() {
        String select = "SELECT "  + Const.HOUSE_NUMBER_HOUSE + "," + Const.HOUSE_NUMBER_OF_FLOORS + ","
            + Const.APARTMENT_NUMBER_FLOOR + "," + Const.APARTMENT_NUMBER_APARTMENT + ","
            + Const.APARTMENT_SQUARE_APARTMENT + "," + Const.APARTMENT_RESIDENT_COUNT
            + " FROM " + Const.HOUSE_TABLE +  " INNER JOIN " + Const.APARTMENT_TABLE + " ON " + Const.HOUSE_ID + " = "
            + Const.APARTMENT_ID_HOUSE + ";";

        List<House> houses = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = prSt.executeQuery();
            resultSet.next();
            int numberHouse;
            do {
                numberHouse = resultSet.getInt(Const.HOUSE_NUMBER_HOUSE);
                houses.add(createHouse(resultSet, numberHouse).get());
                while (resultSet.getInt(Const.HOUSE_NUMBER_HOUSE)!=numberHouse){
                    resultSet.next();
                }
                while (resultSet.getInt(Const.HOUSE_NUMBER_HOUSE)==numberHouse){
                    if(!resultSet.next()){
                        break;
                    }
                }
                if(!resultSet.next()){
                    break;
                }
            }while(resultSet.getInt(Const.HOUSE_NUMBER_HOUSE)!=numberHouse);
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return houses;
    }

    @Override
    public void deleteByKey(Integer idHouse) {
        String delete = "DELETE FROM " + Const.HOUSE_TABLE + " WHERE "
            + Const.HOUSE_ID + "=" + idHouse + ";";

        try {
            Connection connection = connectionPool.getConnection();
            String select = "SELECT " + Const.APARTMENT_NUMBER_APARTMENT
                + " FROM " + Const.APARTMENT_TABLE + " WHERE " + Const.APARTMENT_ID_HOUSE + "=" + idHouse + ";";
            PreparedStatement prSt = connection.prepareStatement(select);

            ResultSet resultSet = prSt.executeQuery();
            int numberApartments = 0;
            while (resultSet.next()){
                numberApartments++;
            }

            for (int i = 1; i <= numberApartments; i++) {
                apartmentRepository.deleteByKey(i, idHouse); ;
            }

            prSt = connection.prepareStatement(delete);
            prSt.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String delete = "DELETE FROM " + Const.HOUSE_TABLE + ";";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(delete);
            apartmentRepository.deleteAll();
            prSt.executeUpdate();
            System.out.println("Успех!");
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> findHouseNumbersWhereCountOfTenantsLimit(Integer limitTenants){
        String select = "SELECT " + Const.APARTMENT_NUMBER_HOUSE + ", SUM(" + Const.APARTMENT_RESIDENT_COUNT + ") FROM "
            + Const.APARTMENT_TABLE + " GROUP BY " + Const.APARTMENT_NUMBER_HOUSE + " HAVING SUM("
            + Const.APARTMENT_RESIDENT_COUNT + ")<" + limitTenants +";";

        List<Integer> houseNumbers = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = prSt.executeQuery();
            while(resultSet.next()) {
                houseNumbers.add(resultSet.getInt(1));
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return houseNumbers;
    }

    public Integer getID(Integer numberOfHouse){
        String select = "SELECT " + Const.HOUSE_ID + " FROM " + Const.HOUSE_TABLE
            + " WHERE " + Const.HOUSE_NUMBER_HOUSE + "="+ numberOfHouse + ";";

        Integer idHouse = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = prSt.executeQuery();
            if(resultSet.next()) {
                idHouse = resultSet.getInt(1);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idHouse;
    }

    public List<Integer> getHouseNumbers(){
        String select = "SELECT "  + Const.HOUSE_NUMBER_HOUSE
            + " FROM " + Const.HOUSE_TABLE + ";";

        List<Integer> houseNumbers = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = prSt.executeQuery();
            while(resultSet.next()){
                houseNumbers.add(resultSet.getInt(1));
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return houseNumbers;
    }

    public Integer getCountFreeApartment(int numberOfHouse) {
        String select = "SELECT sum(case WHEN "+Const.APARTMENT_RESIDENT_COUNT + "=0 "
            + "Then 1 Else 0 end) FROM " + Const.APARTMENT_TABLE
            + " WHERE " + Const.APARTMENT_NUMBER_HOUSE + "=" + numberOfHouse;

        Integer valueOfFlat = 0;

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet resultSet = prSt.executeQuery();

            if(resultSet.next()){
                valueOfFlat = resultSet.getInt(1);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return valueOfFlat;
    }

    public Integer countApartments(Integer numberOfHouse){
        String select = "SELECT COUNT(" + Const.APARTMENT_NUMBER_APARTMENT + ")"
            + " FROM " + Const.HOUSE_TABLE +  " INNER JOIN " + Const.APARTMENT_TABLE
            + " ON " + Const.HOUSE_ID + " = " + Const.APARTMENT_ID_HOUSE
            + " WHERE " + Const.HOUSE_NUMBER_HOUSE + "=" + numberOfHouse + ";";

        Integer numberOfApartments = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);


            ResultSet resultSet = prSt.executeQuery();
            if(resultSet.next()) {
                numberOfApartments = resultSet.getInt(1);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfApartments;
    }

    public Integer countTenantsInHouse(Integer numberOfHouse){
        String select = "SELECT SUM(" + Const.APARTMENT_RESIDENT_COUNT + ")"
            + " FROM " + Const.HOUSE_TABLE +  " INNER JOIN " + Const.APARTMENT_TABLE
            + " ON " + Const.HOUSE_ID + " = " + Const.APARTMENT_ID_HOUSE
            + " WHERE " + Const.HOUSE_NUMBER_HOUSE + "=" + numberOfHouse + ";";

        Integer numberOfTenants = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = prSt.executeQuery();
            if(resultSet.next()) {
                numberOfTenants = resultSet.getInt(1);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return numberOfTenants;
    }

    public Double calculateAreaOfHouse(Integer numberOfHouse){
        String select = "SELECT SUM(" + Const.APARTMENT_SQUARE_APARTMENT + ")"
            + " FROM " + Const.APARTMENT_TABLE +  " WHERE " + Const.APARTMENT_NUMBER_HOUSE + "="
            + numberOfHouse + " UNION SELECT " + Const.HOUSE_NUMBER_HOUSE + " FROM " + Const.HOUSE_TABLE
            + " WHERE " + Const.HOUSE_NUMBER_HOUSE + "=" + numberOfHouse + ";";

        Double areaOfHouse = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet resultSet = prSt.executeQuery();
            if(resultSet.next()) {
                areaOfHouse = resultSet.getDouble(1);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return areaOfHouse;
    }

    private Optional<House> createHouse(ResultSet resultSet, Integer numberOfHouse){
        House house = new House();
        try {
        int numberOfFloors = resultSet.getInt(Const.HOUSE_NUMBER_OF_FLOORS);
        List<Floor> floors = new ArrayList<>();

        for(int i = 1; i <= numberOfFloors; i++){
            Floor floor = new Floor();
            List<Apartment> apartments = new ArrayList<>();
            do{
                if(resultSet.getInt(Const.APARTMENT_NUMBER_FLOOR) == i
                    && resultSet.getInt(Const.HOUSE_NUMBER_HOUSE) == numberOfHouse) {
                    Apartment apartment = new Apartment();
                    apartment.setNumber(resultSet.getInt(Const.APARTMENT_NUMBER_APARTMENT));
                    apartment.setNumberOfHouse(numberOfHouse);
                    apartment.setNumberOfFloor(i);
                    apartment.setSquareApartment(resultSet.getDouble(Const.APARTMENT_SQUARE_APARTMENT));
                    apartment.setResidentCount(resultSet.getInt(Const.APARTMENT_RESIDENT_COUNT));
                    apartments.add(apartment);
                }
            } while(resultSet.next());
            floor.setNumber(i);
            floor.setNumberOfHouse(numberOfHouse);
            floor.setApartments(apartments);
            floors.add(floor);
            resultSet.first();
        }
        house.setNumber(numberOfHouse);
        house.setFloors(floors);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(house);
    }
}
