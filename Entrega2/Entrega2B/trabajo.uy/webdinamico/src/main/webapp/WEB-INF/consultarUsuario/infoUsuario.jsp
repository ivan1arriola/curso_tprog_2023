<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="main.java.logica.datatypes.DTEmpresa" %>
<%@ page import="main.java.logica.datatypes.DTPostulante" %>
<%@ page import="main.java.logica.datatypes.DTUsuario" %>
<%@ page import="java.util.Base64" %>
<!DOCTYPE html>
<html>

<%
// Obtener el objeto de usuario desde los atributos de la solicitud
DTUsuario usuario = (DTUsuario) request.getAttribute("usuario");
boolean editable = (boolean) request.getAttribute("editable");
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
                            // Genera un nombre de archivo único para la imagen
                            String nombreArchivo = "imagen_usuario_" + usuario.getNickname() + ".png";
                            
                            // Ruta en el servidor donde se guardará la imagen temporalmente
                            String rutaTemporal = getServletContext().getRealPath("/temp/");
                            
                            // Crea el directorio temporal si no existe
                            File directorioTemporal = new File(rutaTemporal);
                            if (!directorioTemporal.exists()) {
                                directorioTemporal.mkdirs();
                            }
                            
                            // Guarda la imagen en un archivo temporal
                            File archivoImagen = new File(rutaTemporal, nombreArchivo);
                            try (FileOutputStream fos = new FileOutputStream(archivoImagen)) {
                                fos.write(imagenUsuario);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
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
                        <h2><%= usuario.getcorreoElectronico() %> </h2>
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
            <%if(editable){ %>
            <jsp:include page="./tabPerfilEditable.jsp" />
            <%} else { %>
              <jsp:include page="./tabPerfil.jsp" />
             <%} %>  
            <%if (usuario instanceof DTEmpresa){ %>
            <jsp:include page="./tabOfertasLaborales.jsp" />
            <%} %>

        </div>
    </main>

     <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


    <!-- Otros scripts aquí -->

</body>

</html>

