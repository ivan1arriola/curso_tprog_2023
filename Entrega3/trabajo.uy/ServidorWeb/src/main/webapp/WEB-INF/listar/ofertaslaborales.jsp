<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="java.util.Base64" %>


<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Ofertas Laborales</title>
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
            
            <%  String keyword = request.getParameter("key"); %>
            <% if (keyword != null) { %>
            	
            	<jsp:include page="/WEB-INF/templates/keywordFiltro.jsp" />
           <%} %> 
            
            

            <div class="sin-bordes">
                <%
                	Set<OfertaLaboralBean> ofertasLaborales = (Set<OfertaLaboralBean>) request.getAttribute("ofertasLaborales");
               
                if (ofertasLaborales == null || ofertasLaborales.isEmpty()) {
                    // Si ofertasLaborales es null o está vacío,  muestra un mensaje
                %>
                    <div class="alert alert-secondary" role="alert">
					    <% if (ofertasLaborales == null) { %>
					        No se encontraron resultados para la búsqueda.
					    <% } else if (ofertasLaborales.isEmpty()) { %>
					        No hay ofertas laborales disponibles en este momento.
					    <% } %>
					</div>

                <%
                } else {
                    for (OfertaLaboralBean oferta : ofertasLaborales) {
                        String imagenUrl = oferta.getImagen();

                        if (imagenUrl == null){
                            imagenUrl = request.getContextPath() + "/imagenNoFound.png";
                        }
                     
                        String nombre = oferta.getNombre();
                        String empresa = oferta.getNicknameEmpresa();
                        String descripcion = oferta.getDescripcion();
                        String enlace = request.getContextPath() + "/consultarofertalaboral?o=" + oferta.getNombre();
                %>
                <div class="card mb-3">
				    <div class="row g-0">
				        <div class="col-md-4">
				            <img src="<%=imagenUrl%>" class="img-fluid rounded-start" alt="<%=nombre%>" />
				        </div>
				        <div class="col">
				            <div class="card-body">
				                <h5 class="card-title"><%=nombre%></h5>
				                <h6 class="card-subtitle mb-2 text-muted"><%=empresa%></h6>
				                <p class="card-text"><%=descripcion%></p>
				                <a href="<%=enlace%>" class="card-link">Leer más</a>
				            </div>
				        </div>
				    </div>
				</div>

                <%
                    }
                }
                %>
            </div>
        </div>
    </main>

  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


</body>

</html>
