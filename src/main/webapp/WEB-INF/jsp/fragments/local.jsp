<%@page contentType="text/html" pageEncoding="UTF-8" %>
<script type="text/javascript">
    var locale="${pageContext.response.locale}"
</script>
<div class="dropdown open">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="true">${pageContext.response.locale}<b class="caret"></b></a>
    <ul class="dropdown-menu">
        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?language=en">English</a></li>
        <li><a href="${requestScope['javax.servlet.forward.request_uri']}?language=ru">Русский</a></li>
    </ul>
</div>