<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="main.java.logica.datatypes.DTEmpresa" %>
<%@ page import="main.java.logica.datatypes.DTPostulante" %>
<%@ page import="main.java.logica.datatypes.DTUsuario" %>
<%@ page import="java.util.Base64" %>


<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Usuarios</title>
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

            <div class="sin-bordes">
                <div class="row">
                    <%
                    HashSet<DTUsuario> usuarios = (HashSet<DTUsuario>) request.getAttribute("usuarios");
                    if (usuarios == null || usuarios.isEmpty()) {
                        // Si ofertasLaborales es null o está vacío, muestra un mensaje
                    %>
                        <div class="alert alert-secondary" role="alert">
    					        No hay usuarios dados de alta en este momento.
    					</div>

                    <%
                    } else {
                        for (DTUsuario usuario : usuarios) {
                        	String imagen = usuario.getImagen();

                        	if (imagen == null) {
                        	    imagen = request.getContextPath() + "/nopicture.png";
                        	}
                            %>
                            <div class="col-md-4">
                                <div class="card mb-3">
                                    <img src="<%= imagen %>" class="card-img-top" alt="Perfil de <%= usuario.getNickname() %>" />
                                    <div class="card-body">
                                        <h5 class="card-title">
                                            <a href="<%= request.getContextPath() %>/consultarusuario?u=<%= usuario.getNickname() %>"><%= usuario.getNickname() %></a>
                                        </h5>
                                        <p class="card-text">
                                        	<% 
                                                if (usuario instanceof DTPostulante) {
                                                    out.println("Postulante");
                                                } else if (usuario instanceof DTEmpresa) {
                                                    out.println("Empresa");
                                                }
                                            %>
                                        </p>
                                        
                                    </div>
                                </div>
                            </div>
                            <%
                        }
                    }
                    %>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

</body>

</html>
