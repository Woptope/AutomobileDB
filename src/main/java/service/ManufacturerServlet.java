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

import factory.dao.Manufacturer_dao;
import factory.entity.Manufacturer;


public class ManufacturerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getRequestURI();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertManufacturer(request, response);
                    break;
                case "/delete":
                    deleteManufacturer(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateManufacturer(request, response);
                    break;
            default:
                    listManufacturer(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Manufacturer_dao manuf_dao = new Manufacturer_dao();
        List<Manufacturer> manufacturerList = manuf_dao.getAll();
        request.setAttribute("manufacturerList", manufacturerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ManufacturerList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ManufacturerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Manufacturer_dao manuf_dao = new Manufacturer_dao();
        int id_manufacturer = Integer.parseInt(request.getParameter("id_manufacturer"));
        Manufacturer existingManufacturer = manuf_dao.get(id_manufacturer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ManufacturerForm.jsp");
        request.setAttribute("manufacturer", existingManufacturer);
        dispatcher.forward(request, response);

    }

    private void insertManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Manufacturer_dao manuf_dao = new Manufacturer_dao();
        String title = request.getParameter("title");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");

        Manufacturer newManufacturer = new Manufacturer(title, country, city, phone);
        manuf_dao.add(newManufacturer);
        response.sendRedirect("list");
    }

    private void updateManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Manufacturer_dao manuf_dao = new Manufacturer_dao();
        int id_manufacturer = Integer.parseInt(request.getParameter("id_manufacturer"));
        String title = request.getParameter("title");
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        Manufacturer newManufacturer = new Manufacturer(id_manufacturer, title, country, city, phone);
        manuf_dao.update(newManufacturer);
        response.sendRedirect("list");
    }

    private void deleteManufacturer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Manufacturer_dao manuf_dao = new Manufacturer_dao();
        int id_manufacturer = Integer.parseInt(request.getParameter("id_manufacturer"));
        manuf_dao.delete_by_id(id_manufacturer);
        response.sendRedirect("list");
    }
}
