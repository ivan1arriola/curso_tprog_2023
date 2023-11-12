<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.servidor.DtTipoOferta" %>

    <%
    	String nombrePagina = (String) request.getAttribute("nombrePagina");
    	if (nombrePagina == null) nombrePagina = "Tipo de Publicacion de Oferta Laboral";

        DtTipoOferta tipo = (DtTipoOferta) request.getAttribute("tipoPublicacion");
    	
    
    %>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>
    	<%= nombrePagina %>
    </title>
</head>

<body>
    <header>
        <jsp:include page="/WEB-INF/templates/header.jsp" />
    </header>

    <main class="container-fluid d-flex">
        <div class="container col-3">
            <jsp:include page="/WEB-INF/templates/sidebar.jsp" />
        </div>

        <div class="container container col-9">

        <div class="container">
          <div class="row">
            <h2>Información de Tipo de Publicación de Oferta Laboral</h2>
          </div>

          <div class="row">
            <div class="col-3">
              <span class="fw-bold">Nombre:</span>
            </div>
            <div class="col">
              <span id="nombre"><%= tipo.getNombre() %></span>
            </div>
          </div>

          <div class="row">
            <div class="col-3">
              <span class="fw-bold">Descripción:</span>
            </div>
            <div class="col">
              <span id="descripcion"><%=tipo.getDescripcion() %></span>
            </div>
          </div>

          <div class="row">
            <div class="col-3">
              <span class="fw-bold">Costo:</span>
            </div>
            <div class="col">
              <span id="costo">$<%= tipo.getCosto() %></span>
            </div>
          </div>

          <div class="row">
            <div class="col-3">
              <span class="fw-bold">Duración: (en dias)</span>
            </div>
            <div class="col">
              <span id="duracion"><%= tipo.getDuracion() %></span>
            </div>
          </div>

          <div class="row">
            <div class="col-3">
              <span class="fw-bold">Exposición:</span>
            </div>
            <div class="col">
              <span id="exposicion"><%= tipo.getExposicion() %></span>
            </div>
          </div>
          <div class="row">
            <div class="col-3">
              <span class="fw-bold">Fecha de alta:</span>
            </div>
            <div class="col">
              <span id="fechaAlta"><%= tipo.getFechaAlta() %></span>
            </div>
          </div>
        </div>

      </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


</body>

</html>
