<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <meta charset="UTF-8">
    <title>Error 500 - Error interno del servidor</title>
</head>
<body>
<header>
    <jsp:include page="/WEB-INF/templates/header.jsp" />
</header>
<main class="container row">
    <div class="container col-3">
        <jsp:include page="/WEB-INF/templates/sidebar.jsp" />
    </div>

    <div class="container col-9">
        <jsp:include page="/WEB-INF/templates/navbar.jsp" />
        <h1>Error 500 - Error interno del servidor</h1>
        <p>Lo sentimos,  ha ocurrido un error interno en el servidor al procesar tu solicitud.</p>

        <%
            Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
            String errorMessage = (throwable != null) ? throwable.getMessage() : "No se ha proporcionado información del error.";
        %>

        <p>Información del error: <%= errorMessage %></p>
    </div>
</main>
</body>
</html>
