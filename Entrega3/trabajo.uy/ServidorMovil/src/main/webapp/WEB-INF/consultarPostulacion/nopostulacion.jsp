<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="logica.servidor.DtUsuario" %>
<%@ page import="logica.servidor.DtPostulacion" %>
<%@ page import="java.util.Base64" %>

<%
    DtOfertaExtendido oferta = (DtOfertaExtendido) request.getAttribute("oferta");
	String nick = (String) request.getAttribute("nickname");
    byte[] imagenBytes = oferta.getImagen();
    String imagen = null;
    if (imagenBytes!=null) {
    	imagen = "data:image/jpg;base64,  " + Base64.getEncoder().encodeToString(imagenBytes);
    }
%>

<!DOCTYPE html>
<html>

<head>
  <jsp:include page="/WEB-INF/template/head.jsp" />
  <title>Postulaciones</title>
</head>

<body>
<header>
  <jsp:include page="/WEB-INF/template/navbar.jsp" />
</header>

<main>
  
    
    <div class="d-flex justify-content-center">
		<div class="col-6">
			    <div class="card" style="width: 16rem;">
			  		<img src="<%=imagen %>" class="card-img-top" alt="<%=oferta %>" />
			  		<div class="card-body">
			  		<div class="alert alert-info" role="alert">
			    	<p class="card-text">No existe postulacion de <%=nick%> para <%=oferta.getNombre()%></p>
			    	</div>
			    	</div>
			  		</div>
		</div>
	</div>
    
    
    
</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>