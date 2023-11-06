<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>


<%
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
    String imagen = ofertaLaboral.getImagen();
    if (imagen == null) {
        imagen = request.getContextPath() + "/imagenNoFound.png";
    }
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
    boolean duenioOfertaLaboral = (boolean) request.getAttribute("duenio");
    boolean mostrarContenido = tipoUsuario == TipoUsuario.Empresa || tipoUsuario == TipoUsuario.Postulante;
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title><%= ofertaLaboral.getNombre() %></title>

    <style>
        .banner-container {
            background-image: url(<%= imagen %>);
        }



    </style>
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
        <div class="container">
            <div class="row banner-container banner-dark">
                <h1 class="text-center text-light fw-bolder"><%= ofertaLaboral.getNombre() %></h1>
            </div>

            <div class="row">
                <div class="col">
                    <div id="detalleOferta">
                        <jsp:include page="./detallesOferta.jsp" />
                    </div>
                </div>

                <% if (mostrarContenido) { %>


                <div class="col-4 mt-2 container text-center" id="acciones">
                    <h4> Dar de favorito</h4>
                    <button class="btn btn-primary mb-2">
				        <div class="float-right"> <!-- Clase de Bootstrap para alinear a la derecha -->
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="red" class="bi bi-heart" viewBox="0 0 16 16"> <!-- Icono de corazón de Bootstrap -->
                                <path d="M8 14s6-3.5 6-7a3.5 3.5 0 0 0-7 0C8 10.5 8 14 8 14z"/>
                                <path fill-rule="evenodd" d="M3.906 2.293a.5.5 0 0 1 .708 0L8 6.793l3.387-4.5a.5.5 0 0 1 .763.647l-4 5a.5.5 0 0 1-.75-.001l-4-5a.5.5 0 0 1 .003-.647z"/>
                            </svg>
                        </div>
                    </button>


                    <% if(tipoUsuario == TipoUsuario.Empresa && duenioOfertaLaboral){ %>
                    <jsp:include page="./postulantes.jsp" />
                    <jsp:include page="./paquetes.jsp" />

                    <%} else if (tipoUsuario == TipoUsuario.Postulante){%>
                    <jsp:include page="./postular.jsp" />
                    <%}%>

                </div>
                <% } %>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjvP/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>

</html>
