<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="java.util.Base64" %>

<!DOCTYPE html>
<html>

<%
	DtOfertaExtendido oferta = (DtOfertaExtendido) request.getAttribute("oferta");
	byte[] imagenBytes = oferta.getImagen();
	String imagen = "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(imagenBytes);

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
        <h2>Ya existe una postulación de <%=request.getAttribute("nombre")%> para esta oferta%></h2>

    </div>


</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

</body>
</html>