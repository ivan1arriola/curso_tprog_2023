<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.logica.Datatypes.DTEmpresa" %>
<%@ page import="main.java.logica.Datatypes.DTPostulante" %>
<%@ page import="main.java.logica.Datatypes.DTUsuario" %>
<%@ page import="java.util.Base64" %>

<!DOCTYPE html>
<html>

<%
// Obtener el objeto de usuario desde los atributos de la solicitud
DTUsuario usuario = (DTUsuario) request.getAttribute("usuario");
%>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Información de <%= usuario.getNombre() %></title>
</head>

<body>
    <header>
        <jsp:include page="/WEB-INF/templates/header.jsp" />
    </header>

    <main class="container-fluid d-flex">
        <div class="container col-3">
            <jsp:include page="/WEB-INF/templates/sidebar.jsp" />
        </div>

        <div class="container container col-9 p-4">

            <!-- Agregar contenido nuevo aquí -->
            <div class="container">
                <div class="row">
                    <div class="col-4">
                        <%
                        // Mostrar la imagen del usuario si está disponible
                        byte[] imagenUsuario = usuario.getImagen();
                        if (imagenUsuario != null) {
                            String imagenBase64 = new String(Base64.getEncoder().encode(imagenUsuario), "UTF-8");
                        %>
                            <img src="data:image/png;base64,<%= imagenBase64 %>"
                                alt="Imagen de Usuario" class="img-fluid mb-3 rounded-circle img-thumbnail perfil" />
                        <%
                        } else {
                        %>
                            <img src="<%= request.getContextPath() + "/nopicture.png" %>"
                                alt="Imagen de Usuario por Defecto"
                                class="img-fluid mb-3 rounded-circle img-thumbnail perfil" />
                        <%
                        }
                        %>
                    </div>
                    <div class="col-8">
                        <h1><%= usuario.getNombre() %>  <%= usuario.getApellido() %></h1>
                        <h2><%= usuario.getCorreo_electronico() %> </h2>
                    </div>
                </div>
            </div>

            <!-- Agrega las pestañas (tabs) -->
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <a class="nav-link active" data-bs-toggle="tab" data-bs-target="#perfil-panel" type="button"
                        role="tab" aria-controls="perfil-panel" aria-selected="true">Perfil</a>
                </li>
                <%if (usuario instanceof DTEmpresa){ %>
                <li class="nav-item" role="presentation">
		          <a class="nav-link" data-bs-toggle="tab" data-bs-target="#ofertas-panel" type="button" role="tab"
		            aria-controls="ofertas-panel" aria-selected="false">Ofertas Laborales</a>
		        </li>
		        <%} %>
            </ul>

            <!-- Contenido de las pestañas -->
            <jsp:include page="./tabPerfil.jsp" />
                
            <%if (usuario instanceof DTEmpresa){ %>
            <jsp:include page="./tabOfertasLaborales.jsp" />
            <%} %>

        </div>
    </main>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

    <!-- Otros scripts aquí -->

</body>

</html>

