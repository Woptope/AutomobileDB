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
    <c:if test="${auto != null}">
        <h1>Edit Automobile</h1> 
    </c:if>
    <c:if test="${auto == null}">
        <h1>Add New Automobile</h1>
    </c:if>
    <h2><a href="/auto/list">List All Automobiles</a></h2>
</center>
<div align="center">
    <c:if test= "${auto != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${auto == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1px" cellpadding="5" style="width: 21%">
                <c:if test="${auto != null}">
                    <input type="hidden" name="id_auto" value="<c:out value="${auto.id_auto}" />" />
                </c:if>           
                <tr>
                    <th>Model: </th>
                    <td>
                        <input type="text" name="model" class="form-control" placeholder="Enter Model" value="<c:out value="${auto.model}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Color: </th>
                    <td>
                        <input type="text" name="color" class="form-control" placeholder="Enter Color" value="<c:out value="${auto.color}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Transmission: </th>
                    <td>
                        <input type="text" name="transmission" class="form-control" placeholder="Enter Transmission" value="<c:out value="${auto.transmission}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Car body: </th>
                    <td>
                        <input type="text" name="car_body" class="form-control" placeholder="Enter Car body"  value="<c:out value="${auto.car_body}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Price: </th>
                    <td>
                        <input type="text" name="price" class="form-control" placeholder="Enter Price" value="<c:out value="${auto.price}" />"/>
                    </td>
                </tr>
                <c:if test= "${auto == null}">
                    <tr>
                        <th>Manufacturer: </th>
                            <td>
                                <select class="custom-select" name="id_manufacturer">
                                    <option selected>Select Manufacturer</option>
                                    <c:forEach var="manufacturer" items="${manufacturerList}">
                                    <option value="<c:out value="${manufacturer.id_manufacturer}" />"><c:out value="${manufacturer.title}" /></option>
                                    </c:forEach>
                                </select>
                            </td>
                    </tr>
                </c:if>
                <c:if test= "${auto != null}">
                    <tr>
                        <th>Manufacturer: </th>
                            <td>
                                <select class="custom-select" name="id_manufacturer">
                                    <option selected value="${manuf.id_manufacturer}">${manuf.title}</option>
                                    <c:forEach var="manufacturer" items="${manufacturerList}">
                                        <option value="<c:out value="${manufacturer.id_manufacturer}" />"><c:out value="${manufacturer.title}" /></option>
                                    </c:forEach>
                                </select>
                            </td>
                    </tr>
                </c:if>  


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