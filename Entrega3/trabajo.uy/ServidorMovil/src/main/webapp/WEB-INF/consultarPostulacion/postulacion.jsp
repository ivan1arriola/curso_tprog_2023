<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="logica.servidor.DtOfertaExtendido" %>
<%@ page import="logica.servidor.DtUsuario" %>
<%@ page import="logica.servidor.DtPostulacion" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page import="java.util.regex.Pattern" %>

<%
	DtPostulacion postulacion = (DtPostulacion) request.getAttribute("postulacion");
    DtOfertaExtendido oferta = (DtOfertaExtendido) request.getAttribute("oferta");
    String nombre = (String) request.getAttribute("postulante");
    String nombreOferta = (String) oferta.getNombre();
    byte[] imagenBytes = (byte[]) request.getAttribute("imagenOferta");
    String imagen = null;
    if (imagenBytes!=null) {
    	imagen = "data:image/jpg;base64,  " + Base64.getEncoder().encodeToString(imagenBytes);
    }
    
    String url = postulacion.getUrlVideo();
    boolean esValido = url != null && (url.contains("youtube.com") || url.contains("youtu.be"));
    String video = "";
    
    
    if (esValido) {
        if (!url.contains("embed")) {
            
            String regex1 = "https://youtu.be/(\\w+)";
            String regex2 = "https://www.youtube.com/watch\\?v=(\\w+)";

            String videoId = null;

            // Intenta hacer coincidir con la primera expresión regular
            Pattern pattern1 = Pattern.compile(regex1);
            Matcher matcher1 = pattern1.matcher(url);
            if (matcher1.find()) {
                videoId = matcher1.group(1);
            } else {
                // Si no coincide con la primera,  intenta la segunda expresión regular
                Pattern pattern2 = Pattern.compile(regex2);
                Matcher matcher2 = pattern2.matcher(url);
                if (matcher2.find()) {
                    videoId = matcher2.group(1);
                }
            }
            if (videoId != null) {
                video = "https://www.youtube.com/embed/" + videoId;
            } else {
                // Si no se encontró un identificador válido,  retorna la URL original
                video = url;
            }
        }

    } else {
        // Manejar el caso en el que la URL no sea válida (puedes lanzar una excepción,  establecer un valor predeterminado,  etc.)
        video = null;
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
                        <% if (video != null && video != ""){ %>
                                
                                <iframe
                                        width="100%"
                                        height="395"
                                        src="<%=video%>"
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