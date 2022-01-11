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
    <c:if test="${order != null}">
        <h1>Edit Order</h1> 
    </c:if>
    <c:if test="${order == null}">
        <h1>Add New Order</h1>
    </c:if>
    <h2><a href="/order/list">List All Orders</a></h2>
</center>
    <div align="center">
        <c:if test= "${order != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${order == null}">
                <form action="insert" method="post">
                    </c:if>
                    <table border="1px" cellpadding="5" style="width: 25%">
                        <c:if test="${order != null}">
                            <input type="hidden" name="id_order" value="<c:out value="${order.id_order}" />" />
                        </c:if>    
                        <c:if test= "${order == null}">
                            <tr>
                                <th>Client: </th>
                                <td>
                                    <select class="custom-select" name="id_client">
                                        <option selected>Select client</option>
                                        <c:forEach var="client" items="${clientList}">
                                            <option value="<c:out value="${client.id_client}" />"><c:out value="${client.name}" /></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Automobile: </th>
                                <td>
                                    <select class="custom-select" name="id_auto">
                                        <option selected>Select auto</option>
                                        <c:forEach var="auto" items="${autoList}">
                                            <option value="<c:out value="${auto.id_auto}" />"><c:out value="${auto.model}" /></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Employee: </th>
                                <td>
                                    <select class="custom-select" name="id_employee">
                                        <option selected>Select employee</option>
                                        <c:forEach var="employee" items="${employeeList}">
                                            <option value="<c:out value="${employee.id_employee}" />"><c:out value="${employee.name}" /></option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th>Payment type: </th>
                                <td>
                                    <select class="custom-select" name="payment_type">
                                        <option selected>Select Payment type</option>
                                        <option value="Наличка">Наличка</option>
                                        <option value="Безнал">Безнал</option>
                                    </select>
                                </td>
                            </tr>
                        </c:if>    
                        <c:if test= "${order != null}">   
                        <tr>
                            <th>Client: </th>
                            <td>
                                <select class="custom-select" name="id_client">
                                    <option selected value="${exist_client.id_client}">${exist_client.name}</option>
                                    <c:forEach var="client" items="${clientList}">
                                        <option value="<c:out value="${client.id_client}" />"><c:out value="${client.name}" /></option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Automobile: </th>
                            <td>
                                <select class="custom-select" name="id_auto">
                                    <option selected value="${exist_auto.id_auto}">${exist_auto.model}</option>
                                    <c:forEach var="auto" items="${autoList}">
                                        <option value="<c:out value="${auto.id_auto}" />"><c:out value="${auto.model}" /></option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Employee: </th>
                            <td>
                                <select class="custom-select" name="id_employee">
                                    <option selected value="${exist_employee.id_employee}">${exist_employee.name}</option>
                                    <c:forEach var="employee" items="${employeeList}">
                                        <option value="<c:out value="${employee.id_employee}" />"><c:out value="${employee.name}" /></option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>Payment type: </th>
                            <td>
                                <select class="custom-select" name="payment_type">
                                    <option selected value="${order.payment_type}">${order.payment_type}</option>
                                    <c:if test= "${order.payment_type == 'Наличка'}"> 
                                        <option value="Безнал">Безнал</option>
                                    </c:if> 
                                    <c:if test= "${order.payment_type == 'Безнал'}"> 
                                        <option value="Наличка">Наличка</option>
                                    </c:if> 
                                </select>
                            </td>
                        </tr>
                        </c:if>  
                        <tr>
                            <th>Contract date: </th>
                            <td>
                                <input type="text" name="contract_date" class="form-control"  placeholder="Enter Contract date" value="<c:out value="${order.contract_date}" />"/>
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