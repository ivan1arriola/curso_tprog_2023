<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.logica.datatypes.DTCantTO" %>
<%@ page import="java.util.HashSet" %>

<% %>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title><%= (request.getAttribute("nombrePagina") != null && !((String) request.getAttribute("nombrePagina")).isEmpty()) ? request.getAttribute("nombrePagina") : "Paquete" %></title>
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
            <!-- Contenido aquí -->
            <div class="container mt-5">
                <div class="container mt-5">
                    <div id="results" class="mt-4">
                        <div class="row">
                            <div class="col-md-3">
                                <img src="<%= request.getAttribute("imagenPaquete") %>" alt="Imagen 1" class="img-fluid mb-2" />
                            </div>
                            <div class="col-md">
                                <h2>Información del Paquete</h2>
                                <p><b>Nombre:</b> <%= request.getAttribute("nombrePaquete") %></p>
                                <p><b>Costo del Paquete:</b> <%= request.getAttribute("costoPaquete") %></p>
                                <p><b>Descuento del Paquete:</b> <%= request.getAttribute("descuentoPaquete") %></p>
                                <p><b>Validez del Paquete:</b> <%= request.getAttribute("validezPaquete") %></p>
                                <p><b>Descripción del Paquete:</b> <%= request.getAttribute("descripcionPaquete") %></p>
                                <p><b>Fecha:</b> <%= request.getAttribute("fechaPaquete") %></p>
                            </div>
                            
                            
               
                         
                        </div>
                    </div>
                    <h2 class="mt-4">Tipos de publicación y sus cantidades</h2>
                    <% HashSet<DTCantTO> tiposDePublicacion = (HashSet<DTCantTO>) request.getAttribute("tiposDePublicacion"); %>
					<table class="table" id="tiposDePublicacion">
					    <thead>
					        <tr>
					            <th>Tipo de Publicación</th>
					            <th>Cantidad</th>
					            <th>Acciones</th>
					        </tr>
					    </thead>
					    <tbody>
					        <% for (DTCantTO tipoDePublicacion : tiposDePublicacion) { %>
					            <tr>
					                <td><%= tipoDePublicacion.getNombre() %></td>
					                <td><%= tipoDePublicacion.getCantidad() %></td>
					                <td>
					                    <a href="<%= request.getContextPath() %>/consultartipopublicacion?tp=<%= tipoDePublicacion.getNombre() %>">Ver más</a>
					                </td>
					            </tr>
					        <% } %>
					    </tbody>
					</table>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


</body>

</html>
