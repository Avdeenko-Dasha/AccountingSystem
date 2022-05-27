<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1 align="center">MENU</h1>
    <button class="button"  onclick="document.location='jsp/create-house.jsp'">Create house (yourself)</button>
    <button class="button" onclick="document.location='jsp/create-house-auto.jsp'">Create house (automatically)</button>
    <button class="button" onclick="document.location='jsp/display-house.jsp'">Display information about the house</button>
    <button class="button" onclick="document.location='jsp/delete-house.jsp'">Delete house</button>
    <button class="button" onclick="document.location='jsp/compare.jsp'">Compare houses</button>
    <button class="button" onclick="document.location='jsp/calculate-square-and-residents.jsp'">Calculate square and residents</button>
</body>
</html>
