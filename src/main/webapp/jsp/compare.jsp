<%@ page import="com.avdeenko.service.HouseService" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Compare</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1>COMPARE</h1>
<div class="container">
  <h2 align="center">Created houses: <% out.print(HouseService.getHouseService().getHouseNumbers());%></h2>
  <form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="compare_house">
    <h3>The number of the house you want to compare</h3>
    <input type="number" name="numberOfHouse1" min="1" max = "999" required>
    <input type="number" name="numberOfHouse2" min="1" max = "999" required>
    <button class="button" type="submit">Next</button>
  </form>
</div>
</body>