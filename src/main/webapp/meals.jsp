<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <style>
        .three {
            border-radius: 10px;
            color: #36454A;
            background: -webkit-linear-gradient(top, #A4D3E0, #A4D3E0 50%, #CBE3EB 50%);
            background: -o-linear-gradient(top, #A4D3E0, #A4D3E0 50%, #CBE3EB 50%);
            background: linear-gradient(to top, #A4D3E0, #A4D3E0 50%, #CBE3EB 50%);
            box-shadow: 2px 2px 3px black;
        }
        th {
            font-weight: normal;
            padding: 7px 10px;
        }
        td {
            border-top: 1px solid #FDFFE4;
            padding: 7px 10px;
        }
        tr:nth-child(2n) {
            background: #D7DCE1;
        }
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif, serif;
            font-size: 14px;
            max-width: 70%;
            width: 70%;
            border-collapse: collapse;
            text-align: left;
        }
        .edit:before   { content: "\270E"; }
        .delete:before { content: "\2718";}
    </style>
    <title></title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>User Meals</h2>

<div>
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Дата/Время</th>
            <th>Описание</th>
            <th>Калории</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>

        <jsp:useBean id="mealsList" scope="request" type="java.util.Collection"/>
        <c:forEach var="meal" items="${requestScope.mealsList}">
            <tr style="color:  ${meal.isExceed() ?  '#ff3e25' : '#33bf49 '}">
                <td>${meal.getId()}</td>
                <td>
                    <javatime:parseLocalDateTime value="${meal.getDateTime()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsed"/>
                    <javatime:format value="${parsed}" style="FS"/>
                </td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td><a class="edit" href="meals?action=edit&id=${meal.getId()}">Edit</a></td>
                <td><a class="delete" href="meals?action=delete&id=${meal.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<p><a class="three" href="meals?action=add">Add Meal</a></p>

</body>
</html>
