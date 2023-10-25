<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javabeans.CantTipoPublicacionBean" %>
<%@ page import="javabeans.PaqueteBean" %>
<%@ page import="java.util.Set" %>

<% 

 	boolean mostrarComprar = (boolean) request.getAttribute("mostrarComprar");
	PaqueteBean paquete = (PaqueteBean) request.getAttribute("paquete");

%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
	<title><%= paquete.getNombre() %></title>
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
                                <img src="<%= paquete.getImagen() %>" alt="Imagen 1" class="img-fluid mb-2" />
                            </div>
                            <div class="col-md">
                                <h2>Información del Paquete</h2>
                                <p><b>Nombre:</b> <%= paquete.getNombre() %></p>
                                <p><b>Costo del Paquete:</b> <%= paquete.getCosto() %></p>
                                <p><b>Descuento del Paquete:</b> <%= paquete.getDescuento() %></p>
                                <p><b>Validez del Paquete:</b> <%= paquete.getValidez() %></p>
                                <p><b>Descripción del Paquete:</b> <%= paquete.getDescripcion() %></p>
                                <p><b>Fecha:</b> <%= paquete.getFechaAlta() %></p>
                            </div>
                            
                            
                            <% if ( mostrarComprar){ %>
                            <jsp:include page="./comprarPaquete.jsp" />
                            <%} %>
                         
                        </div>
                    </div>
                    <h2 class="mt-4">Tipos de publicación y sus cantidades</h2>
                    <% Set<CantTipoPublicacionBean> tiposDePublicacion = paquete.getTiposDePub(); %>
					<table class="table" id="tiposDePublicacion">
					    <thead>
					        <tr>
					            <th>Tipo de Publicación</th>
					            <th>Cantidad</th>
					            <th>Acciones</th>
					        </tr>
					    </thead>
					    <tbody>
					        <% for (CantTipoPublicacionBean tipoDePublicacion : tiposDePublicacion) { %>
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
