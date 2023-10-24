<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.datatypes.DTTipoOferta" %>
<%@ page import="java.util.HashSet" %>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Tipos de Publicación</title>
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

            <div class="sin-bordes">
                <div class="row">
                    <%
                        HashSet<DTTipoOferta> tiposOferta = (HashSet<DTTipoOferta>) request.getAttribute("tiposOferta");
                    if (tiposOferta == null || tiposOferta.isEmpty()) {
                        // Si ofertasLaborales es null o está vacío, muestra un mensaje
                    %>
                        <div class="alert alert-secondary" role="alert">
    					        No hay tipos de publicacion de oferta laboral disponibles en este momento.
    					</div>

                    <%
                    } else {
                        for (DTTipoOferta tipoOferta : tiposOferta) {
                    %>
                    <div class="col-md-6">
                        <div class="card mb-3">
                            <div class="card-body">
                                <h5 class="card-title"><%= tipoOferta.getNombre() %></h5>
                                <p class="card-text"><%= tipoOferta.getDescripcion() %></p>
                                <a href="<%= request.getContextPath() %>/consultartipopublicacion?tp=<%= tipoOferta.getNombre() %>" class="card-link">Leer más</a>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    }
                    %>
                </div>
            </div>
        </div>
    </main>

     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


</body>

</html>
