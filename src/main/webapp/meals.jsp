<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <style>
    </style>
    <title>Title</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>User Meals</h2>

<div>
    <table border="2">
        <thead>
        <tr>
            <th>LocalDateTime</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="mealsList" scope="request" type="java.util.List"/>
        <c:forEach var="meal" items="${mealsList}">
            <tr style="color: ${meal.isExceed() ?  'red' : 'green'}">
                <%--<th><javatime:parseLocalDate value="${meal.getDateTime()}" pattern="yyyy-MM-dd'T'HH:mm"/></th>--%>
                <td>
                    <fmt:parseDate value="${meal.getDateTime()}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}"/>
                </td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
