<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>Auto Dealer</title>
    <style>
        #container {
            width: 1000px; /* Ширина макета */
            margin: 0 auto; /* Выравнивание по центру */
        }

        th.sorted[data-order="1"],
        th.sorted[data-order="-1"] {
            position: relative;
        }

        th.sorted[data-order="1"]::after,
        th.sorted[data-order="-1"]::after {
            right: 8px;
            position: absolute;
        }

        th.sorted[data-order="-1"]::after {
            content: "▼"
        }

        th.sorted[data-order="1"]::after {
            content: "▲"
        }
         th {
            cursor: pointer;
        }

    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
   <%-- <script language="javascript" src="script.js" type="text/javascript"></script>--%>
    <script>
        document.addEventListener('DOMContentLoaded', () => {

            const getSort = ({ target }) => {
                const order = (target.dataset.order = -(target.dataset.order || -1));
                const index = [...target.parentNode.cells].indexOf(target);
                const collator = new Intl.Collator(['en', 'ru'], { numeric: true });
                const comparator = (index, order) => (a, b) => order * collator.compare(
                    a.children[index].innerHTML,
                    b.children[index].innerHTML
                );

                for(const tBody of target.closest('table').tBodies)
                    tBody.append(...[...tBody.rows].sort(comparator(index, order)));

                for(const cell of target.parentNode.cells)
                    cell.classList.toggle('sorted', cell === target);
            };

            document.querySelectorAll('.table-striped thead').forEach(tableTH => tableTH.addEventListener('click', () => getSort(event)));

        });
    </script>
    <script>
        function tableSearch() {
            var phrase = document.getElementById('search-text');
            var table = document.getElementById('info-table');
            var regPhrase = new RegExp(phrase.value, 'i');
            var flag = false;
            for (var i = 1; i < table.rows.length; i++) {
                flag = false;
                for (var j = table.rows[i].cells.length - 2; j >= 0; j--) {
                    flag = regPhrase.test(table.rows[i].cells[j].innerHTML);
                    if (flag) break;
                }
                if (flag) {
                    table.rows[i].style.display = "";
                } else {
                    table.rows[i].style.display = "none";
                }

            }
        }
    </script>
</head>
<body>
<div class="jumbtron">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-2" style="top: 20px">
                <center>
                <form action="/">
                    <button class="btn btn-outline-primary" >Производители</button>
                </form>
                <form action="/client" >
                    <button class="btn btn-outline-primary" style="margin-top: 10px">Клиенты</button>
                </form>
                <form action="/auto">
                    <button class="btn btn-outline-primary" style="margin-top: 10px">Автомобили</button>
                </form>
                <form action="/order">
                    <button class="btn btn-outline-primary" style="margin-top: 10px">Заказы</button>
                </form>
                <form action="/employee">
                    <button class="btn btn-outline-primary" style="margin-top: 10px">Сотрудники</button>
                </form>
                </center>
            </div>
            <div class="col-md-10">
                <center>
                    <h1>Employees</h1>
                    <h2>
                        <a href="/employee/new">Add New Employee</a>
                        &nbsp;&nbsp;&nbsp;
                        <a href="/employee/list">List All Employees</a>
                    </h2>
                </center>
                    <div id="container">
                        <div class="col-2" align="left">
                            <input class="form-control" type="text" placeholder="Search" id="search-text" onkeyup="tableSearch()">
                        </div>
                        <br>
                    <table class="table table-striped" id="info-table">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Surname</th>
                            <th>Patronymic</th>
                            <th>Position</th>
                            <th>Telephone</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="employee" items="${employeeList}">
                            <tr>
                                <td><c:out value="${employee.name}" /></td>
                                <td><c:out value="${employee.surname}" /></td>
                                <td><c:out value="${employee.patronymic}" /></td>
                                <td><c:out value="${employee.position}" /></td>
                                <td><c:out value="${employee.telephone}" /></td>
                                <td width="12%">
                                    <a href="/employee/edit?id_employee=<c:out value='${employee.id_employee}' />">Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="/employee/delete?id_employee=<c:out value='${employee.id_employee}' />" class="btn btn-sm btn-outline-danger">X</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
