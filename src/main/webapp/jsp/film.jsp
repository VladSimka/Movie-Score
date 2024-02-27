<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>





<head>
    <title>FIIILM</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            font-size: 24px;
            margin-left: 20px;
        }
        p {
            font-size: 16px;
            line-height: 1.5;
            margin-left: 20px;
        }
        .film-image {
            max-width: 300px;
            height: auto;
            margin-bottom: 20px;
            margin-left: 20px;
        }
        .review-form {
            margin-top: 20px;
            margin-left: 20px;
        }
        .review-form input[type="range"] {
            width: 200px;
        }
        .review-form input[type="submit"] {
            margin-top: 10px;
            padding: 5px 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>

<header class="p-3 text-bg-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"></use>
                </svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/" class="nav-link px-2 text-secondary">Home</a></li>
            </ul>


            <c:if test="${user == null}">
                <div class="text-end nav col-2">
                    <form action="${pageContext.request.contextPath}/auth/login.jsp">
                        <input type="submit" value="Login" class="btn btn-outline-light me-2">
                    </form>
                    <form action="${pageContext.request.contextPath}/auth/register.jsp">
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

<body>
<h1>${film.getTitle()}</h1>
<p>${film.getDescription()}</p>
<img src="<c:url value='/resources/images/${film.getPathToImage()}'/>" alt="Film Image" class="film-image">

<c:if test="${film.getAverageGrade() != 0.0}">
<p>Average grade: ${film.getAverageGrade()}</p>
</c:if>

<c:if test="${user != null}">
    <c:set var="userReview" value="${user.getReviews().stream()
    .filter(r -> r.getFilmId().equals(film.getId())).findFirst().orElse(null)}" />
    <c:choose>
        <c:when test="${userReview != null}">
            <p>Your Rating: ${userReview.grade}</p>
        </c:when>
        <c:otherwise>
            <form action="${pageContext.request.contextPath}/controller?command=add_review&film_id=${film.getId()}" method="post" class="review-form">
                <label for="selection">Your Rating:</label>
                <input type="range" name="selection" id="selection" min="1" max="10" step="1">
                <input type="submit" value="Submit">
            </form>
        </c:otherwise>
    </c:choose>
</c:if>

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