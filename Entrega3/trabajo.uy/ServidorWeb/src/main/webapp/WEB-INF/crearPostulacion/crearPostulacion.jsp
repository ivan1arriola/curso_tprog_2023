<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Postulación a Oferta Laboral</title>
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
        <div class="row">
            <div class="col-8">

                <div class="row">
                    <h1 class="font-weight-bold">Postulación a Oferta Laboral</h1>
                </div>

                <div class="row">
                    <jsp:include page="./formulario.jsp" />
                </div>

            </div>

            <div class="col-4">

                <div class="container text-center">
                    <img src="<%= request.getAttribute("imagenOferta") %>" alt="Imagen de <%=session.getAttribute("nickname")%>"
                         class="img-fluid" />
                    <a href="<%=request.getContextPath() %>/consultarofertalaboral?o=<%= request.getAttribute("oferta") %>"
                       class="text"><%= request.getAttribute("oferta") %></a>
                </div>

            </div>

        </div>

    </div>


</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

</body>
</html>
