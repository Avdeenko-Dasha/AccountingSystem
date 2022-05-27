<%@ page import="com.avdeenko.service.HouseService" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create house</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1 align="center">CREATE HOUSE</h1>
<h2 align="center">Created houses: <% out.print(HouseService.getHouseService().getHouseNumbers());%></h2>
<div class="container">
<form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="create_house">
        <h3>Billing House</h3>
        <p>Number of house</p>
        <input type="number" name="numberOfHouse" min="1" max = "999" required>
        <p>Number of floors</p>
        <input type="number" name="numberOfFloors" min="1" max = "250" required>
        <p>Number of apartments on the floor</p>
        <input type="number" name="numberOfApartments" min="1" max = "50" required>
    <button class="btn" type="submit">Next</button>
</form>
</div>

</body>
</html>