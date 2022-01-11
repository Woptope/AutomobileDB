package factory.dao;

import factory.DBConn;
import factory.entity.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Order_dao extends DBConn implements Idao<Order> {


    @Override
    public Order get(int id) {

        String sql = "SELECT * FROM ORDERS WHERE ID_ORDER=?";
        Order order = new Order();

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                order.setId_order(resultSet.getInt("ID_ORDER"));
                order.setId_client(resultSet.getInt("ID_CLIENT"));
                order.setId_auto(resultSet.getInt("ID_AUTO"));
                order.setId_employee(resultSet.getInt("ID_EMPLOYEE"));
                order.setContract_date(resultSet.getDate("CONTRACT_DATE"));
                order.setPayment_type(resultSet.getString("PAYMENT_TYPE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM ORDERS";

        try (Connection connection = getDBConnection()) {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Order order = new Order();
                order.setId_order(resultSet.getInt("ID_ORDER"));
                order.setId_client(resultSet.getInt("ID_CLIENT"));
                order.setId_auto(resultSet.getInt("ID_AUTO"));
                order.setId_employee(resultSet.getInt("ID_EMPLOYEE"));
                order.setContract_date(resultSet.getDate("CONTRACT_DATE"));
                order.setPayment_type(resultSet.getString("PAYMENT_TYPE"));



                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public void add(Order order) {

        String sql = "INSERT INTO ORDERS (ID_CLIENT, ID_AUTO, ID_EMPLOYEE, CONTRACT_DATE, PAYMENT_TYPE) VALUES(?, ?, ?, ?, ?)";

        try (Connection connection = getDBConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getId_client());
            preparedStatement.setInt(2, order.getId_auto());
            preparedStatement.setInt(3, order.getId_employee());
            preparedStatement.setDate(4, new java.sql.Date(order.getContract_date().getTime()));
            preparedStatement.setString(5, order.getPayment_type());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Order order) {

        String sql = "UPDATE ORDERS SET ID_CLIENT=?, ID_AUTO=?, ID_EMPLOYEE=?, CONTRACT_DATE=?, PAYMENT_TYPE=? WHERE ID_ORDER=?";

        try (Connection connection = getDBConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, order.getId_client());
            preparedStatement.setInt(2, order.getId_auto());
            preparedStatement.setInt(3, order.getId_employee());
            preparedStatement.setDate(4, new java.sql.Date(order.getContract_date().getTime()));
            preparedStatement.setString(5, order.getPayment_type());
            preparedStatement.setInt(6, order.getId_order());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Order order) {

        String sql = "DELETE FROM ORDERS WHERE ID_ORDER=?";

        try (Connection connection = getDBConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getId_order());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete_by_id(int id) {

        String sql = "DELETE FROM ORDERS WHERE ID_ORDER=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
