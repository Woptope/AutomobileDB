package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.dao.Client_dao;
import factory.entity.Client;


public class ClientServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getRequestURI();
        try {
            switch (action) {
                case "/client/new":
                    showNewForm(request, response);
                    break;
                case "/client/insert":
                    insertClient(request, response);
                    break;
                case "/client/delete":
                    deleteClient(request, response);
                    break;
                case "/client/edit":
                    showEditForm(request, response);
                    break;
                case "/client/update":
                    updateClient(request, response);
                    break;
                default:
                    listClient(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        Client_dao client_dao = new Client_dao();
        List<Client> clientList = client_dao.getAll();
        request.setAttribute("clientList", clientList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Client_dao client_dao = new Client_dao();
        int id_client = Integer.parseInt(request.getParameter("id_client"));
        Client existingClient = client_dao.get(id_client);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/ClientForm.jsp");
        request.setAttribute("client", existingClient);
        dispatcher.forward(request, response);

    }

    private void insertClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Client_dao client_dao = new Client_dao();
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        int passport = Integer.parseInt(request.getParameter("passport"));
        String telephone = request.getParameter("telephone");

        Client newClient = new Client(name, surname, patronymic, passport, telephone);
        client_dao.add(newClient);
        response.sendRedirect("list");
    }

    private void updateClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Client_dao client_dao = new Client_dao();
        int id_client = Integer.parseInt(request.getParameter("id_client"));
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        int passport = Integer.parseInt(request.getParameter("passport"));
        String telephone = request.getParameter("telephone");
        Client newClient = new Client(id_client, name, surname, patronymic, passport, telephone);
        client_dao.update(newClient);
        response.sendRedirect("list");
    }

    private void deleteClient(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Client_dao client_dao = new Client_dao();
        int id_client = Integer.parseInt(request.getParameter("id_client"));
        client_dao.delete_by_id(id_client);
        response.sendRedirect("list");
    }
}
