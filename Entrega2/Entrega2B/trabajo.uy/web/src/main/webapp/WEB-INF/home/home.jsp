<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>trabajo.uy</title>
</head>

<body>
    <header>
        <jsp:include page="/WEB-INF/templates/header.jsp" />
    </header>

    <main class="container-fluid d-flex">
        <div class="container col-3">
            <jsp:include page="/WEB-INF/templates/sidebar.jsp" />
        </div>

        <div class="container col-9">
            <!-- Links de navegacion en vertical -->
            <div class="h-100">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/ofertaslaborales">Ofertas Laborales</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/usuarios">Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/tipospublicacion">Tipo de Publicacion de Oferta Laboral</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%= request.getContextPath() %>/paquetes">Paquetes</a>
                    </li>
                </ul>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
        crossorigin="anonymous"></script>

</body>

</html>
