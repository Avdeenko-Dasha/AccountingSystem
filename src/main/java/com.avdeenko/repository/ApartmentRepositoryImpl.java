package com.avdeenko.repository;

import com.avdeenko.database.ConnectionPool;
import com.avdeenko.database.Const;
import com.avdeenko.model.model.Apartment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApartmentRepositoryImpl implements ApartmentRepository {
    private final ConnectionPool connectionPool = new ConnectionPool();
    private static ApartmentRepositoryImpl apartmentRepository;

    private ApartmentRepositoryImpl() {
    }

    public static ApartmentRepositoryImpl getApartmentRepository() {
        if (apartmentRepository == null) {
            apartmentRepository = new ApartmentRepositoryImpl();
        }
        return apartmentRepository;
    }

    @Override
    public void creat(Apartment object, Integer idHouse) {
        String insert = "INSERT INTO " + Const.APARTMENT_TABLE + "("
            + Const.APARTMENT_NUMBER_HOUSE + "," + Const.APARTMENT_NUMBER_FLOOR + ","
            + Const.APARTMENT_NUMBER_APARTMENT + "," + Const.APARTMENT_SQUARE_APARTMENT + ","
            + Const.APARTMENT_RESIDENT_COUNT + "," + Const.APARTMENT_ID_HOUSE + ")" + "VALUES(?,?,?,?,?,?)";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(insert);
            prSt.setInt(1, object.getNumberHouse());
            prSt.setInt(2, object.getNumberFloor());
            prSt.setInt(3, object.getNumber());
            prSt.setDouble(4, object.getSquareApartment());
            prSt.setInt(5, object.getResidentCount());
            prSt.setInt(6, idHouse);

            prSt.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Apartment> readByKey(Integer numberOfApartment, Integer idHouse) {
        String select = "SELECT " + Const.APARTMENT_NUMBER_HOUSE + "," + Const.APARTMENT_NUMBER_FLOOR + ","
            + Const.APARTMENT_NUMBER_APARTMENT + "," + Const.APARTMENT_SQUARE_APARTMENT + ","
            + Const.APARTMENT_RESIDENT_COUNT + " FROM " + Const.APARTMENT_TABLE +  " WHERE " +
            Const.APARTMENT_ID_HOUSE + "=" + idHouse + " AND "
            + Const.APARTMENT_NUMBER_APARTMENT + "=" + numberOfApartment + ";";

        Optional<Apartment> apartmentOptional = null;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select);

            ResultSet resultSet = prSt.executeQuery();
            if(resultSet.next()) {
                apartmentOptional = createApartment(resultSet);
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apartmentOptional;
    }

    @Override
    public List<Apartment> readAll() {
        String select = "SELECT "  + Const.APARTMENT_NUMBER_HOUSE + "," + Const.APARTMENT_NUMBER_FLOOR + ","
            + Const.APARTMENT_NUMBER_APARTMENT + "," + Const.APARTMENT_SQUARE_APARTMENT + ","
            + Const.APARTMENT_RESIDENT_COUNT + " FROM " + Const.APARTMENT_TABLE;

        List<Apartment> apartments = new ArrayList<>();
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();

            while(resultSet.next()) {
                apartments.add(createApartment(resultSet).get());
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    @Override
    public void deleteByKey(Integer numberOfApartment, Integer idHouse) {
        String delete = "DELETE FROM " + Const.APARTMENT_TABLE + " WHERE "
            + Const.APARTMENT_NUMBER_APARTMENT + "=" + numberOfApartment +
            " AND " + Const.APARTMENT_ID_HOUSE + "=" + idHouse + ";";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(delete);

            prSt.executeUpdate();
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        String delete = "DELETE FROM " + Const.APARTMENT_TABLE + ";";

        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(delete);

            prSt.executeUpdate();

            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean checkNormOfLivingSpace(Integer numberOfApartment, Integer idHouse){

        String select = "SELECT ROUND((SELECT " + Const.APARTMENT_SQUARE_APARTMENT + " FROM "+ Const.APARTMENT_TABLE
            +  " WHERE " + Const.APARTMENT_ID_HOUSE + "=" + idHouse + " AND " + Const.APARTMENT_NUMBER_APARTMENT + "="
            + numberOfApartment + ") / (SELECT " + Const.APARTMENT_RESIDENT_COUNT + " FROM "+ Const.APARTMENT_TABLE
            +  " WHERE " + Const.APARTMENT_ID_HOUSE + "=" + idHouse + " AND " + Const.APARTMENT_NUMBER_APARTMENT + "="
            + numberOfApartment + "), 2);";

        boolean result = false;
        double normOfLivingSpace = 10.0;
        try {
            Connection connection = connectionPool.getConnection();
            PreparedStatement prSt = connection.prepareStatement(select);

            ResultSet resultSet = prSt.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getDouble(1)>normOfLivingSpace){
                    result = true;
                }
            }
            connectionPool.returnConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Optional<Apartment> createApartment(ResultSet resultSet){
        Apartment apartment = new Apartment();
        try {
            apartment.setNumberHouse(resultSet.getInt(Const.APARTMENT_NUMBER_HOUSE));
        apartment.setNumberFloor(resultSet.getInt(Const.APARTMENT_NUMBER_FLOOR));
        apartment.setNumber(resultSet.getInt(Const.APARTMENT_NUMBER_APARTMENT));
        apartment.setSquareApartment(resultSet.getDouble(Const.APARTMENT_SQUARE_APARTMENT));
        apartment.setResidentCount(resultSet.getInt(Const.APARTMENT_RESIDENT_COUNT));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.of(apartment);
    }
}
