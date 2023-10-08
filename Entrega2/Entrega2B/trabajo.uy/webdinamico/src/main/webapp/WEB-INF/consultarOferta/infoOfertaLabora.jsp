<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Base64" %>
<%@ page import="auxiliar.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>

<%
    // Obtener el objeto de usuario desde los atributos de la solicitud
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
    byte[] imagen = ofertaLaboral.getImagen();
    String imagenString;
    if (imagen != null) {
        imagenString = new String(Base64.getEncoder().encode(imagen));
    } else {
        imagenString = request.getContextPath() + "/imagenNoFound.png";
    }
    
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
    
    boolean mostrarContenido = tipoUsuario == TipoUsuario.Empresa || tipoUsuario == TipoUsuario.Postulante;
%>

<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title><%= ofertaLaboral.getNombre() %></title>
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
                <div class="row">
                    <h1 class="text-center mt-3">Información Básica de la Oferta Laboral</h1>
                </div>
                
                <div class="row">

                    <div class="col-9 d-flex">
                        <div class="container" id="detalleOferta">
                            <div class="row align-items-center mt-2">
                                    
                                    <div class="col-6">
                                        <img src="<%= imagenString %>" alt="Descripción de la imagen" class="img-fluid ofertaLaboral-img">
                                    </div>
                                    <div class="col-6 text-center">
                                        <h2><%= ofertaLaboral.getNombre() %></h2>
                                    </div>
                                    
                            </div>
                                   
                            
                            <div class="row align-items-center mt-2">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <th>Nombre:</th>
                                            <td><%= ofertaLaboral.getNombre() %></td>
                                        </tr>
                                        <tr>
                                            <th>Descripción:</th>
                                            <td><%= ofertaLaboral.getDescripcion() %></td>
                                        </tr>
                                        <tr>
                                            <th>Remuneración:</th>
                                            <td><%= ofertaLaboral.getRemuneracion() %> pesos uruguayos</td>
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
                                            <td><%= ofertaLaboral.getFechaDeAlta() %></td>
                                        </tr>
                                        <tr>
                                            <th>Costo:</th>
                                            <td><%= ofertaLaboral.getCosto() %></td>
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

                        </div>
                    </div>
                    
                    <div class="col-3 text-center">

                        <% if (mostrarContenido) { %>
                                
                            <jsp:include page="./postulantes.jsp" />
                            <jsp:include page="./paquetes.jsp" />
                            
                        <% } %>
                    
                     </div>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    <!-- Otros scripts aquí -->
</body>

</html>
