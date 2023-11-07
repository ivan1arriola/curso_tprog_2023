<%@page import="logica.servidor.TipoUsuarioNoValido_Exception"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="logica.servidor.DtPostulacion" %>
<%@ page import="java.util.Base64" %>


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

<main class="text-center">

        <h1>Postulaciones</h1>

        <div class="container-fluid" id="ofertas-laborales" >

          <div class="sin-bordes">
          
          <%
          List<DtPostulacion> postulaciones = (List<DtPostulacion>) request.getAttribute("postulaciones");
          List<DtOfertaExtendido> ofertas = (ArrayList<DtOfertaExtendido>) request.getAttribute("ofertas");
          String context = request.getContextPath();
          int tamanio = postulaciones.size();
          
          for (int indice = 0; indice < tamanio; indice++) {
        	 
        	  DtOfertaExtendido offer = ofertas.get(indice);
        	  DtPostulacion dtpos = postulaciones.get(indice);
        	  
        	  byte[] imagenBytes = offer.getImagen();
        	  String imagen = "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(imagenBytes);
      
          %>

            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <img src="<%=imagen %>" class="img-fluid rounded-start h-100" alt="Imagen de <%= offer.getNombre()  %>" />
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <h5 class="card-title"><%= offer.getNombre()  %></h5>
                    <h6 class="card-subtitle mb-2 text-muted">Empresa: <%= offer.getNicknameEmpresaPublicadora()  %></h6>
                    
                    <div style="margin-bottom: 10px;">
                    <a href="<%=context%>/consultarofertalaboral?oferta=<%=offer.getNombre()%>" class="card-link btn btn-primary btn-sm">
                    Detalles de oferta
                    </a>
                    </div>
                    
	                 <h5 class="card title">Detalles de Postulación</h5>
					 <h6 class="card-subtitle mb-2 text-muted"> <strong> Fecha: </strong><%=dtpos.getFecha() %> 
					 </h6>
					 <h6 class="card-subtitle mb-2 text-muted"> <strong> Url Documentos extras: </strong>
					 	<%= dtpos.getURLDocExtras() %>
					 </h6>
					 <h6 class="card-subtitle mb-2 text-muted"> <strong>CV:</strong> <%=dtpos.getCVitae() %>
					 </h6>
					 <h6 class="card-subtitle mb-2 text-muted"> <strong>Motivación:</strong> <%=dtpos.getMotivacion() %>
					 </h6>
					 <h6 class="card-subtitle mb-2 text-muted"> <strong>Url Video:</strong> <a href="<%= dtpos.getUrlVideo()%>" class="card-link btn btn-primary btn-sm">
                    <%=dtpos.getUrlVideo() %>
                    </a>
					 </h6>
                  </div>
                </div>
              </div>
            </div>
            
            <% } %>

            
          </div>
        </div>


    </main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


</body>
</html>