<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>

</head>
<body>

<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"></use>
                </svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
            </ul>


            <c:if test="${user == null}">
                <div class="text-end nav col-2">
                    <form action="${pageContext.request.contextPath}/jsp/login.jsp">
                        <input type="submit" value="Login" class="btn btn-outline-light me-2">
                    </form>
                    <form action="${pageContext.request.contextPath}/jsp/register.jsp">
                        <input type="hidden" name="command" value="register">
                        <input type="submit" value="Register" class="btn btn-outline-light me-2">
                    </form>
                </div>
            </c:if>

            <c:if test="${user != null}">
                <a href="/controller?command=go_to_user_page">${user.getUsername()}</a>
                <div class="text-end nav col-2">
                    <form action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="logout">
                        <input type="submit" value="Logout" class="btn btn-outline-light me-2">
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</header>


</body>
</html>
