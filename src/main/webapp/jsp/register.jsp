<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="register">

    Login:<input type="text" name="username" required/>
    <br>
    Password:<input type="password" name="password" required/>
    <br>

    <input type="submit" name="submit" value="Push">
</form>

<br>
<c:if test="${not empty requestScope.errors}">
    <div style="color: red">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
            <br>
        </c:forEach>
    </div>
</c:if>

</body>
</html>
