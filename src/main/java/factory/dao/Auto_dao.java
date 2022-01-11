package factory.dao;

import factory.DBConn;
import factory.entity.Automobile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Auto_dao extends DBConn implements Idao<Automobile> {


    @Override
    public Automobile get(int id) {

        String sql = "SELECT * FROM AUTOMOBILES WHERE ID_AUTO=?";

        Automobile automobile = new Automobile();
        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                automobile.setId_auto(resultSet.getInt("ID_AUTO"));
                automobile.setModel(resultSet.getString("MODEL"));
                automobile.setColor(resultSet.getString("COLOR"));
                automobile.setTransmission(resultSet.getString("TRANSMISSION"));
                automobile.setCar_body(resultSet.getString("CAR_BODY"));
                automobile.setPrice(resultSet.getInt("PRICE"));
                automobile.setId_manufacturer(resultSet.getInt("ID_MANUFACTURER"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return automobile;
    }

    @Override
    public List<Automobile> getAll() {

        List<Automobile> automobileList = new ArrayList<>();
        String sql = "SELECT * FROM AUTOMOBILES";

        try (Connection connection = getDBConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Automobile automobile = new Automobile();
                automobile.setId_auto(resultSet.getInt("ID_AUTO"));
                automobile.setModel(resultSet.getString("MODEL"));
                automobile.setColor(resultSet.getString("COLOR"));
                automobile.setTransmission(resultSet.getString("TRANSMISSION"));
                automobile.setCar_body(resultSet.getString("CAR_BODY"));
                automobile.setPrice(resultSet.getInt("PRICE"));
                automobile.setId_manufacturer(resultSet.getInt("ID_MANUFACTURER"));

                automobileList.add(automobile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return automobileList;
    }

    @Override
    public void add(Automobile automobile) {

        String sql = "INSERT INTO AUTOMOBILES (MODEL, COLOR, TRANSMISSION, CAR_BODY, PRICE, ID_MANUFACTURER) VALUES(?, ?, ?, ?, ?,?)";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, automobile.getModel());
            preparedStatement.setString(2, automobile.getColor());
            preparedStatement.setString(3, automobile.getTransmission());
            preparedStatement.setString(4, automobile.getCar_body());
            preparedStatement.setInt(5, automobile.getPrice());
            preparedStatement.setInt(6, automobile.getId_manufacturer());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Automobile automobile) {

        String sql = "UPDATE AUTOMOBILES SET MODEL=?, COLOR=?, TRANSMISSION=?, CAR_BODY=?, PRICE=?, ID_MANUFACTURER=? WHERE ID_AUTO=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, automobile.getModel());
            preparedStatement.setString(2, automobile.getColor());
            preparedStatement.setString(3, automobile.getTransmission());
            preparedStatement.setString(4, automobile.getCar_body());
            preparedStatement.setInt(5, automobile.getPrice());
            preparedStatement.setInt(6, automobile.getId_manufacturer());
            preparedStatement.setInt(7, automobile.getId_auto());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Automobile automobile) {

        String sql = "DELETE FROM AUTOMOBILES WHERE ID_AUTO=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, automobile.getId_auto());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete_by_id(int id) {

        String sql = "DELETE FROM AUTOMOBILES WHERE ID_AUTO=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
