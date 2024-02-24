<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login">

    Login:<input type="text" name="username" required/>
    <br>
    Password:<input type="password" name="password" required/>
    <br>

    <input type="submit" name="submit" value="Push">

</form>


</body>
</html>
