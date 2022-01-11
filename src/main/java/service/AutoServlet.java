package service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.dao.Auto_dao;
import factory.dao.Manufacturer_dao;
import factory.entity.Automobile;
import factory.entity.Manufacturer;


public class AutoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getRequestURI();

        try {
            switch (action) {
                case "/auto/new":
                    showNewForm(request, response);
                    break;
                case "/auto/insert":
                    insertAuto(request, response);
                    break;
                case "/auto/delete":
                    deleteAuto(request, response);
                    break;
                case "/auto/edit":
                    showEditForm(request, response);
                    break;
                case "/auto/update":
                    updateAuto(request, response);
                    break;
                default:
                    listAuto(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listAuto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Auto_dao auto_dao = new Auto_dao();
        Manufacturer_dao manuf_dao = new Manufacturer_dao();
        List<Automobile> autoList = auto_dao.getAll();
        List<Manufacturer> manufacturerList = manuf_dao.getAll();
        Map<Integer, Manufacturer> manufacturerList_m = new HashMap<>();
        for (var manuf : manufacturerList) {
            int id = manuf.getId_manufacturer();
            manufacturerList_m.put(id, manuf);
        }

        request.setAttribute("autoList", autoList);
        request.setAttribute("manufacturerList_m", manufacturerList_m);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AutoList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Manufacturer_dao manuf_dao = new Manufacturer_dao();
        List<Manufacturer> manufacturerList = manuf_dao.getAll();
        request.setAttribute("manufacturerList", manufacturerList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/AutoForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Auto_dao auto_dao = new Auto_dao();
        Manufacturer_dao manuf_dao = new Manufacturer_dao();
        int id_auto = Integer.parseInt(request.getParameter("id_auto"));
        Automobile existingAuto = auto_dao.get(id_auto);
        int id_manufacturer = existingAuto.getId_manufacturer();
        Manufacturer manuf = manuf_dao.get(id_manufacturer);
        request.setAttribute("manuf", manuf);

        List<Manufacturer> manufacturerList = manuf_dao.getAll();
        manufacturerList.removeIf(manufacturer -> manufacturer.getId_manufacturer() == id_manufacturer);
        request.setAttribute("manufacturerList", manufacturerList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/AutoForm.jsp");
        request.setAttribute("auto", existingAuto);
        dispatcher.forward(request, response);

    }

    private void insertAuto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Auto_dao auto_dao = new Auto_dao();
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String transmission = request.getParameter("transmission");
        String car_body = request.getParameter("car_body");
        int price = Integer.parseInt(request.getParameter("price"));
        int id_manufacturer = Integer.parseInt(request.getParameter("id_manufacturer"));

        Automobile newAuto = new Automobile(model, color, transmission, car_body, price, id_manufacturer);
        auto_dao.add(newAuto);
        response.sendRedirect("list");
    }

    private void updateAuto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Auto_dao auto_dao = new Auto_dao();
        int id_auto = Integer.parseInt(request.getParameter("id_auto"));
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String transmission = request.getParameter("transmission");
        String car_body = request.getParameter("car_body");
        int price = Integer.parseInt(request.getParameter("price"));
        int id_manufacturer = Integer.parseInt(request.getParameter("id_manufacturer"));

        Automobile newAuto = new Automobile(id_auto, model, color, transmission, car_body, price, id_manufacturer);
        auto_dao.update(newAuto);
        response.sendRedirect("list");
    }

    private void deleteAuto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Auto_dao auto_dao = new Auto_dao();
        int id_auto = Integer.parseInt(request.getParameter("id_auto"));
        auto_dao.delete_by_id(id_auto);
        response.sendRedirect("list");
    }

}

