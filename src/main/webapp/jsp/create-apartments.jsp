<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create house</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1 align="center">CREATE HOUSE</h1>
    <div class="container">
            <form action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="create_apartments">
                <h3>Billing House</h3>
                <c:forEach var="number" begin="1" end="${sessionScope.numberOfApartments}">
                    <p>Area of the apartment № <c:out value="${number}"/></p>
                <input type="number" name="squareApartment${number}" min="1" max = "999" step="0.1" required>
                </c:forEach>
        <button class="btn" type="submit">Next</button>
        </form>
    </div>
</body>
</html>
