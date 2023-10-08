<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="main.java.logica.Datatypes.DTEmpresa" %>
<%@ page import="main.java.logica.Datatypes.DTPostulante" %>
<%@ page import="main.java.logica.Datatypes.DTUsuario" %>
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
                        for (DTUsuario usuario : usuarios) {
                        	byte[] imagenBytes = usuario.getImagen();
                        	String imagen;

                        	if (imagenBytes == null) {
                        	    imagen = request.getContextPath() + "/nopicture.png";
                        	} else {
                        	    // Convierte los bytes de la imagen en una cadena base64
                        	    String base64Image = Base64.getEncoder().encodeToString(imagenBytes);
                        	    // Crea una URL de datos base64
                        	    imagen = "data:image/png;base64," + base64Image;
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
                    %>
                </div>
            </div>
        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js"
        integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa"
        crossorigin="anonymous"></script>
</body>

</html>
