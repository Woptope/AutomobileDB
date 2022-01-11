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
            <c:if test="${manufacturer != null}">
                <h1>Edit Manufacturer</h1> 
            </c:if>
            <c:if test="${manufacturer == null}">
                <h1>Add New Manufacturer</h1>
            </c:if>
            <h2><a href="list">List All Manufacturers</a></h2>
        </center>
        <div align="center">
            <c:if test= "${manufacturer != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${manufacturer == null}">
                <form action="insert" method="post">
            </c:if>
            <table border="1px" cellpadding="5" style="width: 20%">
                <c:if test="${manufacturer != null}">
                    <input type="hidden" name="id_manufacturer" value="<c:out value="${manufacturer.id_manufacturer}" />" />
                </c:if>           
                <tr>
                    <th>Title: </th>
                    <td>
                        <input type="text" name="title" class="form-control"  placeholder="Enter Title" value="<c:out value="${manufacturer.title}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Country: </th>
                    <td>
                        <input type="text" name="country" class="form-control"  placeholder="Enter Country" value="<c:out value="${manufacturer.country}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>City: </th>
                    <td>
                        <input type="text" name="city" class="form-control"  placeholder="Enter City" value="<c:out value="${manufacturer.city}" />"/>
                    </td>
                </tr>
                <tr>
                    <th>Phone: </th>
                    <td>
                        <input type="text" name="phone" class="form-control"  placeholder="Enter Phone" value="<c:out value="${manufacturer.phone}" />"/>
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