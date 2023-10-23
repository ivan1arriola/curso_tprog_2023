<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page import="javabeans.UsuarioBean" %>

<%
    // Obtener el objeto de usuario desde los atributos de la solicitud
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
    String imagen = ofertaLaboral.getImagen();
    if (imagen == null)  {
    	imagen = request.getContextPath() + "/imagenNoFound.png";
    }
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
    boolean mostrarContenido = (tipoUsuario == TipoUsuario.Empresa && ofertaLaboral.isMostrarPostulantes());
    Set<UsuarioBean> postulantes = ofertaLaboral.getPostulantes();
    boolean mostrarPostular = TipoUsuario.Postulante == tipoUsuario && postulantes.isEmpty();
    
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title><%= ofertaLaboral.getNombre() %></title>
    
    <style type="text/css">
    	.banner-container {
		    background-image: url(<%= imagen %>);
		}
    </style>
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
            <div class="container">
                
                <div class="row banner-container banner-dark">
    				<h1 class="text-center text-light fw-bolder"><%= ofertaLaboral.getNombre() %></h1>
				</div>
                
                
                <div class="row">
                    <div class="col">
                    
                    	<div id="detalleOferta">
                    		<jsp:include page="./detallesOferta.jsp" />
                    	</div>
                    	
                    	<div id='postular'>
							<jsp:include page="./postular.jsp" />
                        </div>
                    </div>
                    
                    

                        <% if (mostrarContenido) { %>
                        <div class="col-3 text-center">
                                
                            <jsp:include page="./postulantes.jsp" />
                            
                            <% if(tipoUsuario== TipoUsuario.Empresa){ %>
                            
                            <jsp:include page="./paquetes.jsp" />
                             </div>
                        <%} } %>
                    
                    
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <!-- Otros scripts aquí -->
</body>

</html>
