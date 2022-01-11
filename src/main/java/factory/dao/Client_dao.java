package factory.dao;

import factory.DBConn;
import factory.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Client_dao extends DBConn implements Idao<Client> {


    @Override
    public Client get(int id) {

        String sql = "SELECT * FROM Clients WHERE ID_CLIENT=?";

        Client client = new Client();
        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                client.setId_client(resultSet.getInt("ID_CLIENT"));
                client.setName(resultSet.getString("NAME"));
                client.setSurname(resultSet.getString("SURNAME"));
                client.setPatronymic(resultSet.getString("PATRONYMIC"));
                client.setPassport(resultSet.getInt("PASSPORT"));
                client.setTelephone(resultSet.getString("TELEPHONE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> getAll() {

        List<Client> clientList = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTS";

        try (Connection connection = getDBConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Client client = new Client();
                client.setId_client(resultSet.getInt("ID_CLIENT"));
                client.setName(resultSet.getString("NAME"));
                client.setSurname(resultSet.getString("SURNAME"));
                client.setPatronymic(resultSet.getString("PATRONYMIC"));
                client.setPassport(resultSet.getInt("PASSPORT"));
                client.setTelephone(resultSet.getString("TELEPHONE"));

                clientList.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    @Override
    public void add(Client client) {

        String sql = "INSERT INTO CLIENTS (NAME, SURNAME, PATRONYMIC, PASSPORT, TELEPHONE) VALUES(?, ?, ?, ?, ?)";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getPatronymic());
            preparedStatement.setInt(4, client.getPassport());
            preparedStatement.setString(5, client.getTelephone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {

        String sql = "UPDATE CLIENTS SET NAME=?, SURNAME=?, PATRONYMIC=?, PASSPORT=?, TELEPHONE=? WHERE ID_CLIENT=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, client.getName());
            preparedStatement.setString(2, client.getSurname());
            preparedStatement.setString(3, client.getPatronymic());
            preparedStatement.setInt(4, client.getPassport());
            preparedStatement.setString(5, client.getTelephone());
            preparedStatement.setInt(6, client.getId_client());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Client client) {

        String sql = "DELETE FROM CLIENTS WHERE ID_CLIENT=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, client.getId_client());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete_by_id(int id) {

        String sql = "DELETE FROM CLIENTS WHERE ID_CLIENT=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
