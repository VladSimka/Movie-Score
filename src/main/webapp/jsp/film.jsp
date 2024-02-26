<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FIIILM</title>
</head>
<body>

This is very awesome film ${film}

<c:if test="${user != null}">
    <form action="${pageContext.request.contextPath}/controller?command=add_review&film_id=${film.getId()}" method="post">
        <input type="range" name="selection" min="1" max="10" step="1">
        <input type="submit" value="Submit">
    </form>
</c:if>
</body>
</html>
