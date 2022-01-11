package factory.dao;

import factory.DBConn;
import factory.entity.Manufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Manufacturer_dao extends DBConn implements Idao<Manufacturer> {

    @Override
    public Manufacturer get(int id) {

        String sql = "SELECT * FROM MANUFACTURERS WHERE ID_MANUFACTURER=?";
        Manufacturer manufacturer = new Manufacturer();

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                manufacturer.setId_manufacturer(resultSet.getInt("ID_MANUFACTURER"));
                manufacturer.setTitle(resultSet.getString("TITLE"));
                manufacturer.setCountry(resultSet.getString("COUNTRY"));
                manufacturer.setCity(resultSet.getString("CITY"));
                manufacturer.setPhone(resultSet.getString("PHONE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturer;
    }

    @Override
    public List<Manufacturer> getAll() {
        List<Manufacturer> manufacturerList = new ArrayList<>();
        String sql = "SELECT * FROM MANUFACTURERS";

        try (Connection connection = getDBConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setId_manufacturer(resultSet.getInt("ID_MANUFACTURER"));
                manufacturer.setTitle(resultSet.getString("TITLE"));
                manufacturer.setCountry(resultSet.getString("COUNTRY"));
                manufacturer.setCity(resultSet.getString("CITY"));
                manufacturer.setPhone(resultSet.getString("PHONE"));

                manufacturerList.add(manufacturer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return manufacturerList;
    }

    @Override
    public void add(Manufacturer manufacturer) {


        String sql = "INSERT INTO MANUFACTURERS (TITLE, COUNTRY, CITY, PHONE) VALUES(?, ?, ?, ?)";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, manufacturer.getTitle());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setString(3, manufacturer.getCity());
            preparedStatement.setString(4, manufacturer.getPhone());


            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Manufacturer manufacturer) {
        String sql = "UPDATE MANUFACTURERS SET TITLE=?, COUNTRY=?, CITY=?, PHONE=? WHERE ID_MANUFACTURER=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, manufacturer.getTitle());
            preparedStatement.setString(2, manufacturer.getCountry());
            preparedStatement.setString(3, manufacturer.getCity());
            preparedStatement.setString(4, manufacturer.getPhone());
            preparedStatement.setInt(5, manufacturer.getId_manufacturer());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Manufacturer manufacturer) {

        String sql = "DELETE FROM MANUFACTURERS WHERE ID_MANUFACTURER=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, manufacturer.getId_manufacturer());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void delete_by_id(int id) {

        String sql = "DELETE FROM MANUFACTURERS WHERE ID_MANUFACTURER=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
