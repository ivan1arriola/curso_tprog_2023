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
    String linkAOferta = request.getContextPath() + "/consultarofertalaboral?o=" + oferta.getNombre();
    String imagen = oferta.getImagen();
    if (imagen == null)  {
        imagen = request.getContextPath() + "/imagenNoFound.png";
    }
%>

<head>
    <jsp:include page="/WEB-INF/templates/head.jsp" />
    <title>Postulación</title>
    <style >
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

        <div class="row">
            <div class="container col">
                <div class="row banner-container banner-dark">
                    <h1 class="text-center text-light fw-bolder">Postulacion de <%=nombre%> a <%=oferta.getNombre()%></h1>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <tr>
                            <td><b>Postulante:</b></td>
                            <td><a href="<%= linkAUsuario %>"><%= nombre %></a></td>
                        </tr>

                        <tr>
                            <td><b>Oferta Laboral</b></td>
                            <td> <a href="<%= linkAOferta %>"> <%= oferta.getNombre() %></a> </td>
                        </tr>
                        <tr>
                            <td><b>CV reducido:</b></td>
                            <td><%=postulacion.getCVitae()%></td>
                        </tr>
                        <tr>
                            <td><b>Motivación:</b></td>
                            <td><%=postulacion.getMotivacion()%></td>
                        </tr>
                        <tr>
                            <td><b>Fecha Postulación:</b></td>
                            <td><%=postulacion.getFechaString()%></td>
                        </tr>
                        <tr>
                            <td><b>Video Postulacion:</b></td>
                            <td>
                        <% if(postulacion.getVideo() != null){ %>



                                <iframe
                                        width="100%"
                                        height="395"
                                        src="<%=postulacion.getVideo()%>"
                                        title="Video Postulacion"
                                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                        allowfullscreen>
                                </iframe>


                        <% } else { %>
                                <div class="alert alert-warning" role="alert">
                                    No se ha subido un video.
                                </div>

                        <% }%>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>


        </div>

    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>
