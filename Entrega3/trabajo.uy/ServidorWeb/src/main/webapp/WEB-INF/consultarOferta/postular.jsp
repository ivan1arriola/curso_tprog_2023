<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>

<%@ page import="javabeans.OfertaLaboralBean" %>
<%@ page import="enumeration.TipoUsuario" %>

<%
    OfertaLaboralBean ofertaLaboral = (OfertaLaboralBean) request.getAttribute("ofertaLaboral");    
    TipoUsuario tipoUsuario = (TipoUsuario) session.getAttribute("tipoUsuario");
    boolean mostrarPostular = TipoUsuario.Postulante == tipoUsuario && ofertaLaboral.getPostulantes().isEmpty();
%>

<% if (mostrarPostular) { %>
    <div class="m-auto mb-2">
        <a href="<%= request.getContextPath() %>/crearpostulacion?id=<%= ofertaLaboral.getNombre() %>"
            class="btn btn-primary btn-lg px-4 py-1 mb-3" role="button">Postular a esta oferta</a>
    </div>
<% } else if (TipoUsuario.Postulante == tipoUsuario) { %>
    <div class="m-auto mb-2">
        <a href="<%= request.getContextPath() %>/consultapostulacion?id=<%= ofertaLaboral.getNombre() %>"
            class="btn btn-primary btn-lg px-4 py-1 mb-3" role="button">Ver postulaci�n</a>
    </div>
<% } %>
