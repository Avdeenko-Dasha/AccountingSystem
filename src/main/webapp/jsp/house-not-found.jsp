<html>
<head>
    <meta charset="UTF-8">
    <title>Create house</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1 align="center">DISPLAY INFO HOUSE</h1>
<div class="container">
    <h1 align="center">The house with this number does not exist!!!</h1>
    <h3>You can create a new house with this number</h3>
    <button class="btn" onclick="document.location='jsp/create-house.jsp'">Create house (yourself)</button>
    <button class="btn" onclick="document.location='jsp/create-house-auto.jsp'">Create house (automatically)</button>
    <h3>Go back to the menu</h3>
    <button class="btn" onclick="document.location='index.jsp'">Menu</button>
</div>

</body>
</html>