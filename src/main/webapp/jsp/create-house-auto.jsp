<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.avdeenko.service.HouseService" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Create house auto</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1 align="center">CREATE HOUSE</h1>
<div class="container">
  <h2 align="center">Created houses: <% out.print(HouseService.getHouseService().getHouseNumbers());%></h2>
  <form action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="create_house_auto">
    <h3>Number of house</h3>
    <input type="number" name="numberOfHouse" min="1" max = "999" required>
    <button class="button" type="submit">Next</button>
  </form>
</div>
</body>
