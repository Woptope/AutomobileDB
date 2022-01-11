<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>Auto Dealer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
<center>
    <c:if test="${employee != null}">
        <h1>Edit Employee</h1> 
    </c:if>
    <c:if test="${employee == null}">
        <h1>Add New Employee</h1>
    </c:if>
    <h2><a href="/employee/list">List All Employees</a></h2>
</center>
<div align="center">
    <c:if test= "${employee != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${employee == null}">
        <form action="insert" method="post">
            </c:if>
            <table <%--class="table"--%> border="1px" cellpadding="5" style="width: 20%">
                <c:if test="${employee != null}">
                    <input type="hidden" name="id_employee" value="<c:out value="${employee.id_employee}" />" />
                </c:if>           
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" class="form-control" name="name" placeholder="Enter Name" value="<c:out value="${employee.name}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Surname: </th>
                    <td>
                        <input type="text" class="form-control" name="surname" placeholder="Enter Surname" value="<c:out value="${employee.surname}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Patronymic: </th>
                    <td>
                        <input type="text" class="form-control" name="patronymic" placeholder="Enter Patronymic" value="<c:out value="${employee.patronymic}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Position: </th>
                    <td>
                        <input type="text" class="form-control" name="position" placeholder="Enter Position" value="<c:out value="${employee.position}" />"/>
                    </td>
                </tr>
                </tr>
                <tr>
                    <th>Telephone: </th>
                    <td>
                        <input type="text" class="form-control" name="telephone" placeholder="Enter Telephone" value="<c:out value="${employee.telephone}" />"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" class="btn btn-primary" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>   
</body>
</html>