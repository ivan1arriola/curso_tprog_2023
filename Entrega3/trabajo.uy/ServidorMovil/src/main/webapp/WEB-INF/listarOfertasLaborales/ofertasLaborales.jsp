<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>

<head>
  <jsp:include page="/WEB-INF/template/head.jsp" />
  <title>Ofertas Laborales</title>
</head>

<body>
<header>
  <jsp:include page="/WEB-INF/template/navbar.jsp" />
</header>

<main class="text-center">

        <h1>Ofertas Laborales</h1>

        <div class="container-fluid" id="ofertas-laborales" >

          <div class="sin-bordes">
          
          <%
          List<DtOfertaExtendido> ofertas = (ArrayList<DtOfertaExtendido>) request.getAttribute("ofertas");
          String context = request.getContextPath();
          
          for (DtOfertaExtendido oferta : ofertas){
        	  
        	  byte[] imagenBytes = oferta.getImagen();
        	  String imagen = "data:image/jpg;base64,  " + Base64.getEncoder().encodeToString(imagenBytes);
        	  
        	  
         
          
          %>

            <div class="card mb-3">
              <div class="row g-0">
                <div class="col-md-4">
                  <img src="<%=imagen %>" class="img-fluid rounded-start" alt="Imagen de <%= oferta.getNombre()  %>" />
                </div>
                <div class="col-md-8">
                  <div class="card-body">
                    <h4 class="card-title"><%= oferta.getNombre()  %></h4>
                    <h5 class="card-subtitle mb-2 text-muted">Empresa: <%= oferta.getNicknameEmpresaPublicadora()  %></h5>
                    <p class="card-text">
                      <%= oferta.getDescripcion() %>
                    </p>
                    <a href="<%=context%>/consultarofertalaboral?oferta=<%=oferta.getNombre()%>" class="card-link btn btn-primary btn-lg">
                    Leer más
                    </a>
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