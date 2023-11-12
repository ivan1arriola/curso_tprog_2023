<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/WEB-INF/templates/head.jsp" />
        <meta charset="UTF-8">
        <title>Error 404 - Página no encontrada</title>
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
                <h1>Error 404 - Página no encontrada</h1>
                <p>Lo sentimos, la página que buscas no se encuentra.</p>
            </div>

        </main>
    </body>
</html>
