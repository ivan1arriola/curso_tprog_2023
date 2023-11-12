<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="logica.servidor.DtUsuario" %>
<%@ page import="logica.servidor.DtPostulacion" %>
<%@ page import="java.util.Base64" %>

<%
	DtPostulacion postulacion = (DtPostulacion) request.getAttribute("postulacion");
    DtOfertaExtendido oferta = (DtOfertaExtendido) request.getAttribute("oferta");
    String nombre = (String) request.getAttribute("postulante");
    String nombreOferta = (String) oferta.getNombre();
    byte[] imagenBytes = (byte[]) request.getAttribute("imagenOferta");
    String imagen = null;
    if (imagenBytes!=null) {
    	imagen = "data:image/jpg;base64, " + Base64.getEncoder().encodeToString(imagenBytes);
    }
%>

<!DOCTYPE html>
<html>

<head>
<style>
    .titulo-con-fondo {
      background-image: url("<%=imagen%>");
      background-size: cover; 
      color: white; 
      text-align: center; 
      font-weight: bold; 
    }
  </style>


  <jsp:include page="/WEB-INF/template/head.jsp" />
  <title>Postulaciones</title>
</head>

<body>
<header>
  <jsp:include page="/WEB-INF/template/navbar.jsp" />
</header>


<main>
    <div class="container container col-12">
        

    	  <div class="container p-3">

        <div class="row">
            <div class="container col">
                <div class="row banner-container banner-dark">
                    <h1 class="titulo-con-fondo text-center text-light fw-bolder">Postulacion de <%=nombre%> a <%=nombreOferta%></h1>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <tr>
                            <td><b>Postulante:</b></td>
                            <td><%= nombre %></td>
                        </tr>

                        <tr>
                            <td><b>Oferta Laboral</b></td>
                            <td> <%= oferta.getNombre() %></a> </td>
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
                            <td><%=postulacion.getFecha()%></td>
                        </tr>
                        <tr>
                            <td><b>Video Postulacion:</b></td>
                            <td>
                        <% if (postulacion.getUrlVideo() != null && postulacion.getUrlVideo() != ""){ %>

                                <iframe
                                        width="100%"
                                        height="395"
                                        src="<%=postulacion.getUrlVideo()%>"
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

    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
</body>
</html>