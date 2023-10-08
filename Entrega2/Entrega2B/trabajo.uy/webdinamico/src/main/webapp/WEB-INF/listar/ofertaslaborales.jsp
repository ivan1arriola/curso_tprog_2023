<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="main.java.logica.Datatypes.DTOfertaExtendido" %>
<%@ page import="java.util.Base64" %>


<%
// Obtener el objeto de usuario desde los atributos de la solicitud
DTOfertaExtendido ofertaLaboral = (DTOfertaExtendido) request.getAttribute("ofertaLaboral");
%>

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

            <div class="sin-bordes">
                <%
                	HashSet<DTOfertaExtendido> ofertasLaborales = (HashSet<DTOfertaExtendido>) request.getAttribute("ofertasLaborales");
                    for (DTOfertaExtendido oferta : ofertasLaborales) {
                        String imagenUrl = "";
                        if (oferta.getImagen() != null) {
                            String base64Image = new String(Base64.getEncoder().encode(oferta.getImagen()), "UTF-8");
                            imagenUrl = "data:image/jpeg;base64," + base64Image; // Asume que la imagen es en formato JPEG, ajusta según sea necesario
                        } else {
                            // Si no hay imagen, puedes establecer una imagen de reemplazo o un mensaje aquí
                            imagenUrl = request.getContextPath() +  "/imagenNoFound.png";
                        }
                        String nombre = oferta.getNombre();
                        String descripcion = oferta.getDescripcion();
                        String enlace = request.getContextPath() + "/consultarofertalaboral?o=" + oferta.getNombre();
                %>
                <jsp:include page="/WEB-INF/templates/lista1.jsp">
                    <jsp:param name="imagenUrl" value="<%= imagenUrl %>" />
                    <jsp:param name="nombre" value="<%= nombre %>" />
                    <jsp:param name="descripcion" value="<%= descripcion %>" />
                    <jsp:param name="enlace" value="<%= enlace %>" />
                </jsp:include>
                <%
                    }
                %>
            </div>
        </div>
    </main>

  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>


</body>

</html>
