<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <form method="post" action="users" class="form-inline">
            <label><spring:message code="app.login"/></label>
            <select name="userId" class="form-control mx-3">
                <option value="100000" selected>User</option>
                <option value="100001">Admin</option>
            </select>
            <button type="submit" class="btn btn-primary"><spring:message code="common.select"/></button>
        </form>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>

<%--$("p") 	Будут выбраны все элементы p, которые находятся на странице.--%>
<%--$(".par") 	Будут выбраны все элементы на странице с class="par".--%>
<%--$("#par") 	Будет выбран первый элемент на странице с id="par".--%>
<%--$(this) 	Позволяет выбрать текущий HTML элемент. Щелкните на $(this) слева, чтобы посмотреть пример использования данного селектора в онлайн редакторе.--%>
<%--$("p.par") 	Будут выбраны все элементы p на странице с class="par".--%>
<%--$("p#par") 	Будут выбраны все элементы p на странице с id="par".--%>
<%--$(".par,.header,#heat") 	Будут выбраны все элементы на странице со значениями атрибутов class="par", class="header" и id='heat'.--%>
<%--$("[src]") 	Будут выбраны все элементы на странице, имеющие атрибут src.--%>
<%--$("[src='значение']") 	Будут выбраны все элементы со значениям атрибута src="значение".--%>
<%--$("[src$='.gif']") 	Будут выбраны все элементы со значениями атрибута src заканчивающимися на .gif.--%>
<%--$("div#wrap .par1") 	Будут выбраны все элементы с class=par1, которые находятся внутри элементов div с id=wrap.--%>
<%--$(":input") 	Будут выбраны все input элементы на странице.--%>
<%--$(":тип") 	Будут выбраны все input элементы с <input type='тип' />. Например :button выберет все <input type='button' /> элементы, :text выберет все <input type='text' /> элементы и т.д.--%>