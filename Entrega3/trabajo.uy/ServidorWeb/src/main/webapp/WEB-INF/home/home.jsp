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
			<jsp:include page="/WEB-INF/templates/navbar.jsp" />

            <!-- Contenido de la página de inicio -->
            <%
                if (session.getAttribute("nickname") == null) {
            %>
            <jsp:include page="/WEB-INF/home/homeVisitante.jsp" />
            <%
            } else {
                String nombreUsuario = (String) session.getAttribute("nickname");
            %>
            <div class="alert alert-info">
                <p class="mb-0">¡Bienvenido, <%= nombreUsuario %>!</p>
            </div>
            <%
                }
            %>
            <!-- Resto del contenido de la página de inicio -->




        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

</body>


</html>
