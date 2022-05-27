<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 025 25.05.22
  Time: 23:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <input type="hidden" name="command" value="create_residents">
        <h3>Billing House</h3>
        <c:forEach var="number" begin="1" end="${(sessionScope.allApartments)}">
            <p>Residents in the apartment № <c:out value="${number}"/></p>
            <input type="number" name="residentApartment${number}" min="0" max = "99" required>
        </c:forEach>
        <button class="btn" type="submit">Create house</button>
    </form>
</div>

</body>
</html>

