<%@ page import="factory.dao.Auto_dao" %>
<%@ page import="factory.entity.Automobile" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: WorkStation
  Date: 05.03.2020
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <%
      Auto_dao auto_dao = new Auto_dao();
      List<Automobile> list_test =  auto_dao.getAll();
      String temp = list_test.get(0).getCar_body();
    %>
  </head>
  <body>
  <h1>Model:  <%=temp%></h1>
  $END$
  </body>
</html>
