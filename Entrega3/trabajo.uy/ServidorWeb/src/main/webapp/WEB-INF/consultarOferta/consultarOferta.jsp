<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Base64" %>
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
		    background-size: cover; /* Ajusta el tamaño de la imagen para cubrir todo el contenedor */
		    height: 300px; /* Altura del banner, ajusta según tus necesidades */
		    display: flex;
		    justify-content: center;
		    align-items: center;
		}
		
		.banner-container.banner-dark {
		    position: relative;
		}
		
		.banner-container.banner-dark::before {
		    content: "";
		    position: absolute;
		    top: 0;
		    left: 0;
		    width: 100%;
		    height: 100%;
		    background: rgba(0, 0, 0, 0.5); /* Ajusta el valor alpha (0.5) para controlar la opacidad */
		}
		
		.banner-container h1 {
		    position: relative; /* Para que el texto aparezca encima del fondo oscuro */
		    z-index: 1; /* Asegura que el texto esté encima del fondo oscuro */
		    font-size: 3.5em;
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
            <!-- Contenido aquí -->
                        
            <div class="container">
                
                
                <div class="row banner-container banner-dark">
    				<h1 class="text-center text-light fw-bolder"><%= ofertaLaboral.getNombre() %></h1>
				</div>
                
                
                <div class="row">

                    <div class="col d-flex">
                        <div class="container" id="detalleOferta">
                           
                                   
                            
                            <div class="row align-items-center mt-2">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <th>Nombre:</th>
                                            <td><%= ofertaLaboral.getNombre() %></td>
                                        </tr>
                                        <tr>
                                            <th>Empresa:</th>
                                            <td><%= ofertaLaboral.getNicknameEmpresa() %></td>
                                        </tr>
                                        <tr>
                                            <th>Descripción:</th>
                                            <td><%= ofertaLaboral.getDescripcion() %></td>
                                        </tr>
                                        <tr>
                                            <th>Remuneración:</th>
                                            <td><%= ofertaLaboral.getRemuneracionString() %> pesos uruguayos</td>
                                        </tr>
                                        <tr>
                                            <th>Horario:</th>
                                            <td><%= ofertaLaboral.getHorario() %></td>
                                        </tr>
                                        <tr>
                                            <th>Departamento:</th>
                                            <td><%= ofertaLaboral.getDepartamento() %></td>
                                        </tr>
                                        <tr>
                                            <th>Ciudad:</th>
                                            <td><%= ofertaLaboral.getCiudad() %></td>
                                        </tr>
                                        <tr>
                                            <th>Fecha de Alta:</th>
                                            <td><%= ofertaLaboral.getFechaDeAltaString() %></td>
                                        </tr>
                                        <tr>
                                            <th>Costo:</th>
                                            <td><%= ofertaLaboral.getCostoString() %>  pesos uruguayos</td>
                                        </tr>
                                    </tbody>
                                </table>
                                
                            </div>
                            
                            

                            <div>
							    <p class="fw-bold">
							        Keywords:
							    </p>
							    <ul class="list-group list-group-horizontal">
							        <% Set<String> keywords = ofertaLaboral.getKeywords();
							           if (keywords.isEmpty()) { %>
							            <div class="alert alert-warning" role="alert">
							                No hay palabras clave disponibles
							            </div>
							        <% } else {
							           for (String keyword : keywords) { %>
							            <a href="<%= request.getContextPath() %>/ofertaslaborales?key=<%= keyword %>" class="list-group-item">
							            	<%= keyword %>
							            </a>
							        <%   }
							           } %>
							    </ul>
							</div>
							
							
							<%if(mostrarPostular){ %>
							<div class="m-auto mt-5">
							    <a href="<%= request.getContextPath() %>/crearpostulacion?id=<%= ofertaLaboral.getNombre() %>" class="btn btn-primary btn-lg px-4 py-1" role="button">Postular a esta oferta</a>
							</div>

							
							<%} else if( TipoUsuario.Postulante == tipoUsuario){  %>
							<div class="m-auto mt-5">
							    <a href="<%= request.getContextPath() %>/consultapostulacion?id=<%= ofertaLaboral.getNombre() %>" class="btn btn-primary btn-lg px-4 py-1" role="button">Ver postulación</a>
							</div>
							
							<%} %>

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
