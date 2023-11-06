<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="java.util.Base64" %>

<!DOCTYPE html>
<html>

<%
	String nombreOferta = (String) request.getAttribute("nombreOferta");
	DtOfertaExtendido oferta = (DtOfertaExtendido) request.getAttribute("oferta"); 
	byte[] imagenBytes = oferta.getImagen();
	String imagen = null;
    if(imagenBytes!=null) {
    	imagen = "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(imagenBytes);
    }
	
%>

<head>
    <jsp:include page="/WEB-INF/template/head.jsp" />
    <title>Postulación a Oferta Laboral</title>
      <style type="text/css">
    	.banner-container {
		    background-image: url("<%=imagen%>");
		}
  </style>
</head>

<body>
	<header>
	  <jsp:include page="/WEB-INF/template/navbar.jsp" />
	</header>

<main class="container-fluid d-flex">


    <div class="container col-9">
        <div class="row">
            <div class="col-8">

                <div class="row">
                    <h1 class="font-weight-bold">Postulación a Oferta Laboral</h1>
                </div>

                <div class="row">
                    <form class="form-signin needs-validation" novalidate action="crearpostulacion" method="post">

					      <!-- CV Abreviado -->
					      <div class="form-group">
					        <label for="cvAbreviado" class="form-label">CV Abreviado:</label>
					        <textarea class="form-control" name="cvAbreviado" id="cvAbreviado" rows="4" placeholder="CV Abreviado"></textarea>
					      </div>
					
					      <!-- Motivación -->
					      <div class="form-group">
					        <label for="motivacion" class="form-label">Motivación:</label>
					        <textarea class="form-control" name="motivacion" id="motivacion" rows="4" placeholder="Motivación"></textarea>
					      </div>
					
					        <!-- Video de YouTube -->
					        <div class="form-group">
					            <label for="videoYouTube" class="form-label">Enlace de Video de YouTube:</label>
					            <input type="text" class="form-control" name="videoYouTube" id="videoYouTube" placeholder="Enlace de Video de YouTube">
					        </div>
					
					
					      <input type="hidden" name="nombreOferta" value="<%= nombreOferta %>">
					      <input type="hidden" name="nombrePostulante" value="<%= session.getAttribute("nickname") %>">
					      <input type="hidden" name="fechaPostulacion" id="fechaPostulacion" readonly disabled />
					
					      <button class="btn btn-primary" id="btnPostularse" type="submit">
					        Postularse
					      </button>
					
					</form>
					
					<script src="<%= request.getContextPath() %>/js/crearPostulacion.js"></script>
                </div>

            </div>

            <div class="col-4">

                <div class="container text-center">
                    <img src="<%= imagen %>" alt="Imagen de <%=nombreOferta%>"
                         class="img-fluid" />
                    <a href="<%=request.getContextPath() %>/consultarofertalaboral?oferta=<%= nombreOferta %>"
                       class="text"><%= nombreOferta%></a>
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