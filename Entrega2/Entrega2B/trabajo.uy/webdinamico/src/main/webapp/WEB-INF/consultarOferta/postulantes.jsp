<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Set" %>
<%@ page import="auxiliar.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page import="main.java.logica.datatypes.DTUsuario" %>
<%@ page import="java.util.Base64" %>
<%@ page import="auxiliar.OfertaLaboralBean" %>

<%
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario"); // Se supone que no se visualiza para Visitante
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
    Set<DTUsuario> postulantes = ofertaLaboral.getPostulantes();
%>

<div>
    <div class="mt-4" id="postulaciones">
        <h1 class="text-center">Postulaciones</h1>
    </div>

    <div class="container">
        <%
            if ( postulantes.isEmpty()) {
                 if (tipoUsuario == TipoUsuario.Empresa) {
        %>
        <!-- Mensaje para Empresas -->
        <div class="alert alert-warning">
            No hay postulantes para esta oferta en este momento.
        </div>
        <%
                }
            } else {
                // Si hay postulantes, muestra la lista de postulantes como lo hiciste anteriormente
                for (DTUsuario postulante : postulantes) { 
                    byte[] imagenBytes = postulante.getImagen();
                    String imagenBase64 = "";

                    if (imagenBytes != null && imagenBytes.length > 0) {
                        // Convierte los bytes de la imagen en una cadena base64
                        imagenBase64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(imagenBytes);
                    } else {
                        imagenBase64 = request.getContextPath() +  "/imagenNoFound.png";
                    }
        %>
        <!-- Imagen del postulante -->
        <div class="d-flex align-items-center mt-2">
            <img src="<%= imagenBase64 %>" alt="<%= postulante.getNombre() %>" class="img-fluid" style="width: 100px; height: 100px">
            <a href="<%= request.getContextPath() %>/consultarusuario?u=<%= postulante.getNickname() %>" target="_blank" class="ms-4">
                <%= postulante.getNickname() %>
            </a>
        </div>
        <%
                }
            }
        %>
    </div>
</div>