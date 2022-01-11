package service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.Date;

import factory.dao.*;
import factory.entity.*;


public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getRequestURI();

        try {
            switch (action) {
                case "/order/new":
                    showNewForm(request, response);
                    break;
                case "/order/insert":
                    insertOrder(request, response);
                    break;
                case "/order/delete":
                    deleteOrder(request, response);
                    break;
                case "/order/edit":
                    showEditForm(request, response);
                    break;
                case "/order/update":
                    updateOrder(request, response);
                    break;
                default:
                    listOrder(request, response);
                    break;
            }
        } catch (SQLException | ParseException ex) {
            throw new ServletException(ex);
        }
    }

    private void listOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Order_dao order_dao = new Order_dao();
        Auto_dao auto_dao = new Auto_dao();
        Client_dao client_dao = new Client_dao();
        Employee_dao employee_dao = new Employee_dao();
        List<Order> orderList = order_dao.getAll();
        List<Client> clientList = client_dao.getAll();
        List<Employee> employeeList = employee_dao.getAll();
        List<Automobile> autoList = auto_dao.getAll();
        request.setAttribute("orderList", orderList);

        for (var client : clientList) {
            client.setName(client.getSurname().concat(" ").concat(client.getName()).concat(" ").concat(client.getPatronymic()));
        }
        for (var employee : employeeList) {
            employee.setName(employee.getSurname().concat(" ").concat(employee.getName()).concat(" ").concat(employee.getPatronymic()));
        }

        Map<Integer, Client> clientList_m = new HashMap<>();
        for (var client : clientList) {
            int id = client.getId_client();
            clientList_m.put(id, client);
        }

        Map<Integer, Employee> employeeList_m = new HashMap<>();
        for (var employee : employeeList) {
            int id = employee.getId_employee();
            employeeList_m.put(id, employee);
        }

        Map<Integer, Automobile> autoList_m = new HashMap<>();
        for (var auto : autoList) {
            int id = auto.getId_auto();
            autoList_m.put(id, auto);
        }
        request.setAttribute("clientList_m", clientList_m);
        request.setAttribute("employeeList_m", employeeList_m);
        request.setAttribute("autoList_m", autoList_m);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Employee_dao employee_dao = new Employee_dao();
        Client_dao client_dao = new Client_dao();
        Auto_dao auto_dao = new Auto_dao();
        List<Automobile> autoList = auto_dao.getAll();
        List<Client> clientList = client_dao.getAll();
        List<Employee> employeeList = employee_dao.getAll();
        for (var client : clientList) {
            client.setName(client.getSurname().concat(" ").concat(client.getName()).concat(" ").concat(client.getPatronymic()));
        }
        for (var employee : employeeList) {
            employee.setName(employee.getSurname().concat(" ").concat(employee.getName()).concat(" ").concat(employee.getPatronymic()));
        }
        request.setAttribute("autoList", autoList);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("clientList", clientList);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Employee_dao employee_dao = new Employee_dao();
        Auto_dao auto_dao = new Auto_dao();
        Client_dao client_dao = new Client_dao();
        Order_dao order_dao = new Order_dao();
        int id_order = Integer.parseInt(request.getParameter("id_order"));
        Order existingOrder = order_dao.get(id_order);
        int id_client = existingOrder.getId_client();
        int id_auto = existingOrder.getId_auto();
        int id_employee = existingOrder.getId_employee();

        Client client = null;
        Automobile auto = auto_dao.get(id_auto);
        Employee employee = null;

        List<Automobile> autoList = auto_dao.getAll();
        List<Client> clientList = client_dao.getAll();
        List<Employee> employeeList = employee_dao.getAll();

        for (var client_m : clientList) {
            client_m.setName(client_m.getSurname().concat(" ").concat(client_m.getName()).concat(" ").concat(client_m.getPatronymic()));
            if (client_m.getId_client() == id_client) client = client_m;
        }
        for (var employee_m : employeeList) {
            employee_m.setName(employee_m.getSurname().concat(" ").concat(employee_m.getName()).concat(" ").concat(employee_m.getPatronymic()));
            if (employee_m.getId_employee() == id_employee) employee = employee_m;
        }


        //String client_fio = client.getSurname().concat(" ").concat(client.getName()).concat(" ").concat(client.getPatronymic());
        request.setAttribute("exist_client", client);

        //String auto_model = auto.getModel();
        request.setAttribute("exist_auto", auto);

        //String employee_fio = employee.getSurname().concat(" ").concat(employee.getName()).concat(" ").concat(employee.getPatronymic());
        request.setAttribute("exist_employee", employee);


        clientList.removeIf(client_m -> client_m.getId_client() == id_client);
        employeeList.removeIf(employee_m -> employee_m.getId_employee() == id_employee);
        autoList.removeIf(auto_m -> auto_m.getId_auto() == id_auto);

        request.setAttribute("autoList", autoList);
        request.setAttribute("employeeList", employeeList);
        request.setAttribute("clientList", clientList);


        RequestDispatcher dispatcher = request.getRequestDispatcher("/OrderForm.jsp");
        request.setAttribute("order", existingOrder);
        dispatcher.forward(request, response);

    }

    private void insertOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Order_dao order_dao = new Order_dao();
        int id_client = Integer.parseInt(request.getParameter("id_client"));
        int id_employee = Integer.parseInt(request.getParameter("id_employee"));
        int id_auto = Integer.parseInt(request.getParameter("id_auto"));
        Date contract_date = formatter.parse(request.getParameter("contract_date"));
        String payment_type = request.getParameter("payment_type");

        Order newOrder = new Order(id_client, id_employee, id_auto, contract_date, payment_type);
        order_dao.add(newOrder);
        response.sendRedirect("list");
    }

    private void updateOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Order_dao order_dao = new Order_dao();
        int id_order = Integer.parseInt(request.getParameter("id_order"));
        int id_client = Integer.parseInt(request.getParameter("id_client"));
        int id_employee = Integer.parseInt(request.getParameter("id_employee"));
        int id_auto = Integer.parseInt(request.getParameter("id_auto"));
        Date contract_date = formatter.parse(request.getParameter("contract_date"));
        String payment_type = request.getParameter("payment_type");

        Order newOrder = new Order(id_order, id_client, id_employee, id_auto, contract_date, payment_type);
        order_dao.update(newOrder);
        response.sendRedirect("list");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Order_dao order_dao = new Order_dao();
        int id_order = Integer.parseInt(request.getParameter("id_order"));
        order_dao.delete_by_id(id_order);
        response.sendRedirect("list");
    }

}

