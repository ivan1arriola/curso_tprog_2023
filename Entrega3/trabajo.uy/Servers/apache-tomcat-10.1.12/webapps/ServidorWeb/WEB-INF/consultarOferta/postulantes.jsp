<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Set" %>
<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>
<%@ page import="javabeans.UsuarioBean" %>
<%@ page import="java.util.Base64" %>
<%@ page import="javabeans.OfertaLaboralBean" %>

<%
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario"); // Se supone que no se visualiza para Visitante
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");
    Set<UsuarioBean> postulantes = ofertaLaboral.getPostulantes();
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
                for (UsuarioBean postulante : postulantes) { 
                    String imagen = postulante.getImagen();
                   

                    if (imagen == null){
                    	imagen = request.getContextPath() +  "/imagenNoFound.png";
                    }
        %>
        <!-- Imagen del postulante -->
        <div class="d-flex align-items-center mt-2">
            <img src="<%= imagen %>" alt="<%= postulante.getNombre() %>" class="img-fluid" style="width: 100px; height: 100px">
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