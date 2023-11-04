<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="javabeans.PostulacionBean" %>
<%@ page import="javabeans.OfertaLaboralBean" %>

<!DOCTYPE html>
<html>

<%
    PostulacionBean postulacion = (PostulacionBean) request.getAttribute("postulacion");
    OfertaLaboralBean oferta = (OfertaLaboralBean) request.getAttribute("oferta");
    String nombre = (String) request.getAttribute("postulante");
    String linkAUsuario = request.getContextPath() + "/consultarusuario?u=" + postulacion.getNicknamePostulante();
%>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Postulación</title>
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
        <div class="d-flex">
            <div class="contenedor-hijo col-10">
                <h3>Postulación a Oferta Laboral</h3>

                <p><b>Postulante:</b> <a href="<%= linkAUsuario %>"><%= nombre %></a></p>

                <div class="label">
                    <p><b>CV reducido:</b> <%=postulacion.getCVitae()%></p>
                    <p><b>Motivación:</b> <%=postulacion.getMotivacion()%></p>
                    <p><b>Fecha Postulación:</b> <%=postulacion.getFechaString()%></p>
                </div>
            </div>
            <div class="contenedor-hijo-derecha col-2">
                <img src="<%= oferta.getImagen()%>" alt="Imagen de <%= oferta.getNombre()%>" class="img-fluid mb-2" style="width: 160px; height: 120px">
                <p style="text-decoration: underline"><a href="<%= request.getContextPath() %>/consultarofertalaboral?o=<%= oferta.getNombre() %>"> <%= oferta.getNombre() %></a></p>
            </div>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
