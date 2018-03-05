<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <style>
    </style>
    <title>Редактирование</title>
</head>

<body>

<h2>Добавление/Редактирование</h2>

<%--<jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>--%>
<form method="POST" action='meals'>

    <label><input type="text" readonly="readonly" name="id" value="${requestScope.meal.id}" hidden/></label> <br/>

    Date/time :
    <%--<javatime:parseLocalDate value="${requestScope.meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate"/>--%>
    <%--<javatime:parseLocalTime value="${requestScope.meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseTime"/>--%>
    <%--<label><input type="date" name="date" value="${parseDate}" required></label>--%>
    <%--<label><input type="time" name="time" value="${parseTime}" required> </label> <br/>--%>
    <javatime:parseLocalDateTime value="${requestScope.meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDateTime"/>
    <label><input type="datetime-local" name="dateTime" value="${parseDateTime}" required></label><br/>

    Description : <label> <input type="text" name="description" value="${requestScope.meal.description}" required></label> <br/>

    Callories : <label> <input type="text" name="calories" value="${requestScope.meal.calories}" required></label> <br/>

    <input type="submit" value="Submit"/>  <input type="button" onclick="history.back();" value="Back"/>

</form>

</body>
</html>
