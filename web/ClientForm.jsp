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
    <c:if test="${client != null}">
        <h1>Edit Client</h1> 
    </c:if>
    <c:if test="${client == null}">
        <h1>Add New Client</h1>
    </c:if>
    <h2><a href="/client/list">List All Clients</a></h2>
</center>
<div align="center">
    <c:if test= "${client != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${client == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1px" cellpadding="5" style="width: 20%">

                <c:if test="${client != null}">
                    <input type="hidden" name="id_client"  value="<c:out value="${client.id_client}" />" />
                </c:if>           
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="name" class="form-control" placeholder="Enter Name" size="45" value="<c:out value="${client.name}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Surname: </th>
                    <td>
                        <input type="text" name="surname" class="form-control"  placeholder="Enter Surname" value="<c:out value="${client.surname}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Patronymic: </th>
                    <td>
                        <input type="text" name="patronymic" class="form-control"  placeholder="Enter Patronymic" value="<c:out value="${client.patronymic}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Passport: </th>
                    <td>
                        <input type="text" name="passport" class="form-control"  placeholder="Enter Passport" value="<c:out value="${client.passport}" />"/>
                    </td>
                </tr>
                </tr>
                <tr>
                    <th>Telephone: </th>
                    <td>
                        <input type="text" name="telephone" class="form-control"  placeholder="Enter Telephone" value="<c:out value="${client.telephone}" />"/>
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