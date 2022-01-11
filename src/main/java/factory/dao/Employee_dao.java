package factory.dao;

import factory.DBConn;
import factory.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Employee_dao extends DBConn implements Idao<Employee> {


    @Override
    public Employee get(int id) {

        String sql = "SELECT * FROM EMPLOYEES WHERE ID_EMPLOYEE=?";
        Employee employee = new Employee();

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                employee.setId_employee(resultSet.getInt("ID_EMPLOYEE"));
                employee.setName(resultSet.getString("NAME"));
                employee.setSurname(resultSet.getString("SURNAME"));
                employee.setPatronymic(resultSet.getString("PATRONYMIC"));
                employee.setPosition(resultSet.getString("POSITION"));
                employee.setTelephone(resultSet.getString("TELEPHONE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM EMPLOYEES";

        try (Connection connection = getDBConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId_employee(resultSet.getInt("ID_EMPLOYEE"));
                employee.setName(resultSet.getString("NAME"));
                employee.setSurname(resultSet.getString("SURNAME"));
                employee.setPatronymic(resultSet.getString("PATRONYMIC"));
                employee.setPosition(resultSet.getString("POSITION"));
                employee.setTelephone(resultSet.getString("TELEPHONE"));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void add(Employee employee) {

        String sql = "INSERT INTO EMPLOYEES (NAME, SURNAME, PATRONYMIC, POSITION, TELEPHONE) VALUES(?, ?, ?, ?, ?)";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getPatronymic());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setString(5, employee.getTelephone());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Employee employee) {

        String sql = "UPDATE EMPLOYEES SET NAME=?, SURNAME=?, PATRONYMIC=?, POSITION=?, TELEPHONE=? WHERE ID_EMPLOYEE=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setString(3, employee.getPatronymic());
            preparedStatement.setString(4, employee.getPosition());
            preparedStatement.setString(5, employee.getTelephone());
            preparedStatement.setInt(6, employee.getId_employee());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Employee employee) {

        String sql = "DELETE FROM EMPLOYEES WHERE ID_EMPLOYEE=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, employee.getId_employee());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete_by_id(int id) {

        String sql = "DELETE FROM EMPLOYEES WHERE ID_EMPLOYEE=?";

        try (Connection connection = getDBConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
