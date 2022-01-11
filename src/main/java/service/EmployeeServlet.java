package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.dao.Employee_dao;
import factory.entity.Employee;


public class EmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getRequestURI();
        try {
            switch (action) {
                case "/employee/new":
                    showNewForm(request, response);
                    break;
                case "/employee/insert":
                    insertEmployee(request, response);
                    break;
                case "/employee/delete":
                    deleteEmployee(request, response);
                    break;
                case "/employee/edit":
                    showEditForm(request, response);
                    break;
                case "/employee/update":
                    updateEmployee(request, response);
                    break;
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Employee_dao employee_dao = new Employee_dao();
        List<Employee> employeeList = employee_dao.getAll();
        request.setAttribute("employeeList", employeeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Employee_dao employee_dao = new Employee_dao();
        int id_employee = Integer.parseInt(request.getParameter("id_employee"));
        Employee existingEmployee = employee_dao.get(id_employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployeeForm.jsp");
        request.setAttribute("employee", existingEmployee);
        dispatcher.forward(request, response);

    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Employee_dao employee_dao = new Employee_dao();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String position = request.getParameter("position");
        String telephone = request.getParameter("telephone");

        Employee newEmployee = new Employee(name, surname, patronymic, position, telephone);
        employee_dao.add(newEmployee);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Employee_dao employee_dao = new Employee_dao();
        int id_employee = Integer.parseInt(request.getParameter("id_employee"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String position = request.getParameter("position");
        String telephone = request.getParameter("telephone");
        Employee newEmployee = new Employee(id_employee, name, surname, patronymic, position, telephone);
        employee_dao.update(newEmployee);
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Employee_dao employee_dao = new Employee_dao();
        int id_employee = Integer.parseInt(request.getParameter("id_employee"));
        employee_dao.delete_by_id(id_employee);
        response.sendRedirect("list");
    }
}
