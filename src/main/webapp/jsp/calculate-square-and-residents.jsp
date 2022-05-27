<%@ page import="com.avdeenko.service.HouseService" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calculate square</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1 align="center">DISPLAY INFO HOUSE</h1>
<div class="container">
    <h2 align="center">Created houses: <% out.print(HouseService.getHouseService().getHouseNumbers());%></h2>
    <form action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="calculate_square_and_residents">
        <h3>Number of house</h3>
        <input type="number" name="numberOfHouse" min="1" max = "999" required>
        <button class="button" type="submit">Next</button>
    </form>
</div>
</body>