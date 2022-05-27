<html>
<head>
    <meta charset="UTF-8">
    <title>Create house</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h1 align="center">DISPLAY INFO HOUSE</h1>
<div class="container">
        <h4>${(sessionScope.house.toString())}</h4>
        <button class="btn" onclick="document.location='index.jsp'">Menu</button>
</div>

</body>
</html>